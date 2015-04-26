package org.akala.server.user.service.impl;

import java.util.Map;

import org.akala.server.user.service.AkalaMobileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.util.ExpiringMap;
import org.springframework.stereotype.Service;

@Service("akalaMobileService")
public class AkalaMobileServiceImpl implements AkalaMobileService {
  
  private static Map<String, String> credentialsMap = new ExpiringMap<String, String>(60, 1);

  public String sendMobileCredentials(String mobile) {
    String credentials = generateCredentials();
    credentialsMap.put(mobile, credentials);
    return credentials;
  }

  public boolean validMobileCredentials(String mobile, String credentials) {
    String existCredentials = credentialsMap.get(mobile);
    if (StringUtils.isNotEmpty(existCredentials) && existCredentials.equals(credentials)) {
      return true;
    }
    return false;
  }
  
  private String generateCredentials() {
    int randomValue = (int) ((Math.random() * 9 + 1) * 100000);
    return String.valueOf(randomValue);
  }

}
