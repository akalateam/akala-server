package org.akala.server.repository;

import org.akala.server.AkalaApplicationTests;
import org.akala.server.user.bean.AkalaUser;
import org.akala.server.user.bean.Login;
import org.akala.server.user.repository.AkalaUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AkalaUserRepositoryTests extends AkalaApplicationTests {

  @Autowired
  private AkalaUserRepository akalaUserRepository;

  @Test
  public void testSave() {
    AkalaUser akalaUser = AkalaUserFactory.genAkalaUserShane();
    akalaUserRepository.save(akalaUser);
  }

  @Test
  public void testFindByLoginsKey() {
    System.out.println(akalaUserRepository.findOneByLoginsKeyAndLoginsType("18688197043",
        Login.LOGIN_TYPE_PHONE));
  }

  @Test
  public void testDeleteAll() {
    akalaUserRepository.deleteAll();
  }
}
