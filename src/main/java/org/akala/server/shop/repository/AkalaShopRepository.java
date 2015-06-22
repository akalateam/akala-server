package org.akala.server.shop.repository;

import java.math.BigInteger;
import java.util.List;

import org.akala.server.shop.bean.AkalaShop;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AkalaShopRepository extends MongoRepository<AkalaShop, BigInteger> {
  
  /*
   * create index before
   * db.akalaShop.ensureIndex({coordinate:'2d'})
   * db.akalaShop.ensureIndex({coordinate:'2dsphere'})
   */
  @Query(value = "{coordinate : {$geoNear : [?0, ?1]}}")
  public List<AkalaShop> findByCoordinateLongitudeAndCoordinateLatitude(float longitude, float latitude);
  
//  public List<AkalaShop> findByCoordinateNear(Point point);
  
  public GeoResults<AkalaShop> findByCoordinateNear(Point point, Distance distance);
  
}
