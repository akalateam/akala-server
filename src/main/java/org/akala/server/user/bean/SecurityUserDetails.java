package org.akala.server.user.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUserDetails extends User {

  public static final String USER_KEY_TYPE_SEP = "|";
  public static final String USER_KEY_TYPE_SEP_REG = "\\|";

  private static final long serialVersionUID = 1L;

  private AkalaUser akalaUser;

  public SecurityUserDetails(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public AkalaUser getAkalaUser() {
    return akalaUser;
  }

  public void setAkalaUser(AkalaUser akalaUser) {
    this.akalaUser = akalaUser;
  }
}
