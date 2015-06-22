package org.akala.server.shop.service;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;
import org.springframework.data.geo.GeoResult;

public interface AkalaShopService {
  
  public List<GeoResult<AkalaShop>> findAkalaShopList(float longitude, float latitude);
  
  public List<AkalaShopItem> findAkalaShopItemListByShopId(BigInteger shopId);

}
