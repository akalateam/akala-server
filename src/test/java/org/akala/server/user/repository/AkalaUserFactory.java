package org.akala.server.user.repository;

import java.util.ArrayList;
import java.util.List;

import org.akala.server.user.bean.AkalaUser;
import org.akala.server.user.bean.Basic;
import org.akala.server.user.bean.Login;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AkalaUserFactory {
  public static AkalaUser genAkalaUserShane() {
    AkalaUser akalaUser = new AkalaUser();

    Basic basic = new Basic();
    basic.setPhone("18688197043");
    basic.setEmail("ksieee@gmail.com");
    akalaUser.setBasic(basic);

    List<Login> logins = new ArrayList<Login>();
    akalaUser.setLogins(logins);

    Login phongLogin = new Login();
    phongLogin.setKey(basic.getPhone());
    phongLogin.setPwd(new BCryptPasswordEncoder().encode("Password1"));
    phongLogin.setType(Login.LOGIN_TYPE_PHONE);
    logins.add(phongLogin);

    Login emailLogin = new Login();
    emailLogin.setKey(basic.getEmail());
    emailLogin.setPwd(new BCryptPasswordEncoder().encode("Password2"));
    emailLogin.setType(Login.LOGIN_TYPE_EMAIL);
    logins.add(emailLogin);

    return akalaUser;
  }
}
