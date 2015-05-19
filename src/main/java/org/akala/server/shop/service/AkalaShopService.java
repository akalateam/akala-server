package org.akala.server.shop.service;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;

public interface AkalaShopService {
  
  public List<AkalaShop> findAkalaShopList(float longitude, float latitude);
  
  public List<AkalaShopItem> findAkalaShopItemListByShopId(BigInteger shopId);

}
