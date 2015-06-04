package org.akala.server.shop.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;
import org.akala.server.shop.repository.AkalaShopRepository;
import org.akala.server.shop.service.AkalaShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("akalaShopService")
public class AkalaShopServiceImpl implements AkalaShopService {
  
  @Autowired
  private AkalaShopRepository akalaShopRepository;

  @Override
  public List<AkalaShop> findAkalaShopList(float longitude, float latitude) {    
//    return akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(longitude, latitude);
    
//    Point point = new Point(longitude, latitude);
//    GeoResults<AkalaShop> geoResults = akalaShopRepository.findByCoordinateNear(point);
//    //return akalaShopRepository.findByCoordinateNear(point);
//    List<AkalaShop> akalaShopList = new ArrayList<AkalaShop>();
//    for (GeoResult<AkalaShop> geoResult : geoResults) {
//      akalaShopList.add(geoResult.getContent());
//    }
//    return akalaShopList;
    return akalaShopRepository.findAll();
  }

  @Override
  public List<AkalaShopItem> findAkalaShopItemListByShopId(BigInteger shopId) {
    // TODO Auto-generated method stub
    return null;
  }

}
