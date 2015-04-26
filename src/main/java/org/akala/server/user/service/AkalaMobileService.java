package org.akala.server.user.service;

public interface AkalaMobileService {
  
  public String sendMobileCredentials(String mobile);
  
  public boolean validMobileCredentials(String mobile, String credentials);

}
