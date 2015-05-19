package org.akala.server.shop.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;
import org.akala.server.shop.repository.AkalaShopRepository;
import org.akala.server.shop.service.AkalaShopService;
import org.springframework.beans.factory.annotation.Autowired;

public class AkalaShopServiceImpl implements AkalaShopService {
  
  @Autowired
  private AkalaShopRepository akalaShopRepository;

  @Override
  public List<AkalaShop> findAkalaShopList(float longitude, float latitude) {
    return akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(longitude, latitude);
  }

  @Override
  public List<AkalaShopItem> findAkalaShopItemListByShopId(BigInteger shopId) {
    // TODO Auto-generated method stub
    return null;
  }

}
