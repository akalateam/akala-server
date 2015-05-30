package org.akala.server.user.repository;

import java.math.BigInteger;

import org.akala.server.user.bean.AddressInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressInfoRepository extends PagingAndSortingRepository<AddressInfo, BigInteger> {

}
