package org.akala.server.user.service;

import org.springframework.security.provisioning.UserDetailsManager;

public interface AkalaUserDetailsManager extends UserDetailsManager {
  public boolean authUser(String userKey, String userType, String password);

  public boolean resetPwd(String userKey, String userType);
}
