package org.akala.server.user.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.akala.server.config.prop.EmailProp;
import org.akala.server.user.bean.AkalaUser;
import org.akala.server.user.bean.Basic;
import org.akala.server.user.bean.Login;
import org.akala.server.user.bean.SecurityUserDetails;
import org.akala.server.user.repository.AkalaUserRepository;
import org.akala.server.user.service.AkalaUserDetailsManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("akalaMongoUserDetailsManager")
public class AkalaMongoUserDetailsManager implements AkalaUserDetailsManager {

  private final Log logger = LogFactory.getLog(getClass());
  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

  private AuthenticationManager authenticationManager;

  private AkalaUserRepository akalaUserRepository;

  private JavaMailSender sender;

  private EmailProp emailProp;

  @Autowired
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Autowired
  public void setAkalaUserRepository(AkalaUserRepository akalaUserRepository) {
    this.akalaUserRepository = akalaUserRepository;
  }

  @Autowired
  public void setSender(JavaMailSender sender) {
    this.sender = sender;
  }

  @Autowired
  public void setEmailProp(EmailProp emailProp) {
    this.emailProp = emailProp;
  }

  @Override
  public void createUser(UserDetails user) {
    validateUserDetails4Create(user);
    AkalaUser akalaUser = createAkalaUser(user);
    akalaUserRepository.save(akalaUser);
  }

  private void validateUserDetails4Create(UserDetails user) {
    Assert.hasText(user.getUsername(), "Username may not be empty or null");
    boolean userExists = userExists(user.getUsername());
    Assert.isTrue(!userExists, "User has been exists");

    String[] tokens = user.getUsername().split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];

    Assert.hasText(key, "Key may not be empty or null");
    Assert.hasText(type, "Type may not be empty or null");
  }

  private AkalaUser createAkalaUser(UserDetails user) {
    AkalaUser akalaUser = new AkalaUser();

    SecurityUserDetails secUserDetails = (SecurityUserDetails) user;
    secUserDetails.setAkalaUser(akalaUser);

    String[] tokens = user.getUsername().split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];
    String pwd = new BCryptPasswordEncoder().encode(user.getPassword());

    createLogin(key, type, pwd, akalaUser);
    createBasic(key, type, pwd, akalaUser);

    return akalaUser;
  }

  private void createLogin(String key, String type, String pwd, AkalaUser akalaUser) {
    Login login = new Login();
    login.setKey(key);
    login.setType(type);
    login.setPwd(pwd);
    akalaUser.getLogins().add(login);
  }

  private void createBasic(String key, String type, String pwd, AkalaUser akalaUser) {
    Basic basic = new Basic();
    if (Login.LOGIN_TYPE_PHONE.equals(type)) {
      basic.setPhone(key);
    } else if (Login.LOGIN_TYPE_EMAIL.equals(type)) {
      basic.setEmail(key);
    }
    akalaUser.setBasic(basic);
  }

  @Override
  public void updateUser(UserDetails user) {
    SecurityUserDetails secUserDetails = (SecurityUserDetails) user;
    AkalaUser akalaUser = secUserDetails.getAkalaUser();
    Assert.notNull(akalaUser.getId(), "Id may not be empty or null for update user");
    akalaUserRepository.save(akalaUser);
  }

  @Override
  public void deleteUser(String username) {
    AkalaUser akalaUser = loadOneUserByUsername(username);
    akalaUserRepository.delete(akalaUser);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
    String newPwd = new BCryptPasswordEncoder().encode(newPassword);
    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

    if (currentUser == null) {
      // This would indicate bad coding somewhere
      throw new AccessDeniedException(
          "Can't change password as no Authentication object found in context "
              + "for current user.");
    }

    String username = currentUser.getName();

    // If an authentication manager has been set, re-authenticate the user
    // with the supplied password.
    if (authenticationManager != null) {
      logger.debug("Reauthenticating user '" + username + "' for password change request.");

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
          oldPassword));
    } else {
      logger.debug("No authentication manager set. Password won't be re-checked.");
    }

    logger.debug("Changing password for user '" + username + "'");

    SecurityUserDetails userDetails = (SecurityUserDetails) currentUser.getPrincipal();
    updateAkalaUserPwd(userDetails, newPwd, username);

    SecurityContextHolder.getContext().setAuthentication(
        createNewAuthentication(currentUser, newPwd));
  }

  private void updateAkalaUserPwd(SecurityUserDetails userDetails, String newPwd, String username) {
    String[] tokens = username.split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];

    AkalaUser akalaUser = userDetails.getAkalaUser();
    List<Login> logins = akalaUser.getLogins();
    for (Login login : logins) {
      if (StringUtils.equals(login.getKey(), key) && StringUtils.equals(login.getType(), type)) {
        login.setPwd(newPwd);
        break;
      }
    }

    updateUser(userDetails);
  }

  private Authentication createNewAuthentication(Authentication currentAuth, String newPassword) {
    UserDetails user = loadUserByUsername(currentAuth.getName());

    UsernamePasswordAuthenticationToken newAuthentication =
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    newAuthentication.setDetails(currentAuth.getDetails());

    return newAuthentication;
  }

  @Override
  public boolean userExists(String username) {
    List<AkalaUser> users = loadUsersByUsername(username);

    if (users.size() > 1) {
      throw new IncorrectResultSizeDataAccessException("More than one user found with name '"
          + username + "'", 1);
    }

    return users.size() == 1;
  }

  private List<AkalaUser> loadUsersByUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      return null;
    }

    String[] tokens = username.split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];
    return akalaUserRepository.findByLoginsKeyAndLoginsType(key, type);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AkalaUser user = loadOneUserByUsername(username);
    if (user == null) {
      logger.debug("Query returned no results for user '" + username + "'");

      throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound",
          new Object[] {username}, "Username {0} not found"));
    }
    return createUserDetails(user, username);
  }

  private AkalaUser loadOneUserByUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      return null;
    }

    String[] tokens = username.split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];
    return akalaUserRepository.findOneByLoginsKeyAndLoginsType(key, type);
  }

  private UserDetails createUserDetails(AkalaUser user, String username) {
    String[] tokens = username.split(SecurityUserDetails.USER_KEY_TYPE_SEP_REG);
    String key = tokens[0];
    String type = tokens[1];

    Login thisLogin = null;
    List<Login> logins = user.getLogins();
    for (Login login : logins) {
      if (StringUtils.equals(login.getKey(), key) && StringUtils.equals(login.getType(), type)) {
        thisLogin = login;
        break;
      }
    }

    SecurityUserDetails userDetails =
        new SecurityUserDetails(username, thisLogin.getPwd(), new ArrayList<GrantedAuthority>());
    userDetails.setAkalaUser(user);
    return userDetails;
  }

  @Override
  public boolean authUser(String userKey, String userType, String password) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(userKey + SecurityUserDetails.USER_KEY_TYPE_SEP
            + userType, password);
    try {
      authenticationManager.authenticate(token);
      return true;
    } catch (BadCredentialsException expected) {
      return false;
    }
  }

  @Override
  public boolean resetPwd(String userKey, String userType) {
    String newPwd = RandomStringUtils.randomAlphanumeric(6);
    System.out.print(newPwd);
    AkalaUser user = akalaUserRepository.findOneByLoginsKeyAndLoginsType(userKey, userType);
    List<Login> logins = user.getLogins();
    for (Login login : logins) {
      if (login.getKey().equals(userKey) && login.getType().equals(userType)) {
        login.setPwd(new BCryptPasswordEncoder().encode(newPwd));
        akalaUserRepository.save(user);
        if (Login.LOGIN_TYPE_PHONE.equals(userType)) {
          // TODO send new pwd by mobile
        } else if (Login.LOGIN_TYPE_EMAIL.equals(userType)) {
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setFrom(emailProp.getEmailFrom());
          simpleMailMessage.setTo(userKey);
          simpleMailMessage.setSubject(emailProp.getEmailResetpwdSubject());
          simpleMailMessage.setText(MessageFormat.format(emailProp.getEmailResetpwdContent(),
              newPwd));
          sender.send(simpleMailMessage);
        }
        return true;
      }
    }
    return false;
  }

}
