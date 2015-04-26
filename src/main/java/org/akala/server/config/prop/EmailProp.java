package org.akala.server.config.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:props/email.properties")
public class EmailProp {

  @Value("${email.from}")
  private String emailFrom;

  @Value("${email.resetpwd.subject}")
  private String emailResetpwdSubject;

  @Value("${email.resetpwd.content}")
  private String emailResetpwdContent;


  public String getEmailFrom() {
    return emailFrom;
  }

  public void setEmailFrom(String emailFrom) {
    this.emailFrom = emailFrom;
  }

  public String getEmailResetpwdSubject() {
    return emailResetpwdSubject;
  }

  public void setEmailResetpwdSubject(String emailResetpwdSubject) {
    this.emailResetpwdSubject = emailResetpwdSubject;
  }

  public String getEmailResetpwdContent() {
    return emailResetpwdContent;
  }

  public void setEmailResetpwdContent(String emailResetpwdContent) {
    this.emailResetpwdContent = emailResetpwdContent;
  }
}
