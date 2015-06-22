package org.akala.server.shop.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;
import org.akala.server.shop.repository.AkalaShopRepository;
import org.akala.server.shop.service.AkalaShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service("akalaShopService")
public class AkalaShopServiceImpl implements AkalaShopService {
  
  @Autowired
  private AkalaShopRepository akalaShopRepository;
  
  public static Metric metric = new Metric() {    
    private static final long serialVersionUID = 1L;

    @Override
    public double getMultiplier() {
      return 6378137;
    }
  };

  @Override
  public List<GeoResult<AkalaShop>> findAkalaShopList(float longitude, float latitude) {    
//    return akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(longitude, latitude);
    
    Point point = new Point(longitude, latitude);
    Distance distance = new Distance(200, Metrics.KILOMETERS);
    GeoResults<AkalaShop> result = akalaShopRepository.findByCoordinateNear(point, distance);
    return result.getContent();
  }

  @Override
  public List<AkalaShopItem> findAkalaShopItemListByShopId(BigInteger shopId) {
    // TODO Auto-generated method stub
    return null;
  }

}
