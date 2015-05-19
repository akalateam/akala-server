package org.akala.server.user.service;

import java.util.ArrayList;

import org.akala.server.AkalaApplicationTests;
import org.akala.server.user.bean.SecurityUserDetails;
import org.akala.server.user.repository.AkalaUserRepository;
import org.akala.server.user.service.AkalaUserDetailsManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AkalaMongoUserDetailsManagerTest extends AkalaApplicationTests {

  @Autowired
  private AkalaUserDetailsManager akalaUserDetailsManager;

  @Autowired
  private AkalaUserRepository akalaUserRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  private void printBanner(String param) {
    System.out.print("=================================================");
    System.out.print(param);
    System.out.println("=================================================");
  }

  @Test
  public void testSuite() {
    this.testCreateUser();
    this.testAuthenticate("Password1");
    this.testUserExists();
    this.testChangePassword();
    this.testAuthenticate("Password2");
    this.testDeleteUser();
  }

  public void testCreateUser() {
    UserDetails user =
        new SecurityUserDetails("18688197043|Phone", "Password1", new ArrayList<GrantedAuthority>());
    akalaUserDetailsManager.createUser(user);

    printBanner("after create user");
    System.out.println(akalaUserRepository.findAll());
  }

  public void testAuthenticate(String password) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken("18688197043|Phone", password);

    try {
      Authentication auth = authenticationManager.authenticate(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (BadCredentialsException expected) {
      Assert.assertTrue(false);
    }

    printBanner("after Authenticate, Context Authentication "
        + SecurityContextHolder.getContext().getAuthentication());
  }

  public void testUserExists() {
    boolean exists = akalaUserDetailsManager.userExists("18688197043|Phone");

    printBanner("check user exists (" + exists + ")");
  }

  public void testChangePassword() {
    akalaUserDetailsManager.changePassword("Password1", "Password2");

    printBanner("after change password");
  }

  public void testDeleteUser() {
    akalaUserDetailsManager.deleteUser("18688197043|Phone");

    printBanner("after delete user");
    System.out.println(akalaUserRepository.findAll());
  }
}
