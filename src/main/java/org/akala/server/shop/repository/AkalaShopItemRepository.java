package org.akala.server.shop.repository;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShopItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AkalaShopItemRepository extends MongoRepository<AkalaShopItem, BigInteger> {
  
  public List<AkalaShopItem> findByShopId(BigInteger shopId);
  
}
