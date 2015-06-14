package org.akala.server.utils;

public interface AkalaSmsService {
  
  /**
   * send the text to targeted mobile
   * @param mobile
   * @param text
   * @return result return from 3rd party sms service provider
   */
  public String sendSms(final String mobile,final String text);
  
  /**
   * send the credential text to targeted mobile
   * @param mobile
   * @param credential
   * @return status: TRUE - succeed, FALSE - failed.
   */
  public boolean sendCredentialText(final String mobile,final String credential);
  
}
