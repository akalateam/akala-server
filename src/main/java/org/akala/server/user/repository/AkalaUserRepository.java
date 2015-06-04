package org.akala.server.user.repository;

import java.util.List;

import org.akala.server.user.bean.AkalaUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AkalaUserRepository extends PagingAndSortingRepository<AkalaUser, String> {

  List<AkalaUser> findByLoginsKeyAndLoginsType(String key, String type);

  AkalaUser findOneByLoginsKeyAndLoginsType(String key, String type);
}
