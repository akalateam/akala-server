package org.akala.server.shop.repository;

import java.util.List;

import org.akala.server.AkalaApplicationTests;
import org.akala.server.shop.bean.AkalaShop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class AkalaShopRepositoryTests extends AkalaApplicationTests {
  
  @Autowired
  private AkalaShopRepository akalaShopRepository;
  
  @Test
  public void testSave() {
    AkalaShop shePing = AkalaShopFactory.genAkalaShePing();
    akalaShopRepository.save(shePing);
    
    AkalaShop daShiPu = AkalaShopFactory.genAkalaShopDaShiPu();
    akalaShopRepository.save(daShiPu);
    
    AkalaShop heJi = AkalaShopFactory.genAkalaShopHeJi();
    akalaShopRepository.save(heJi);
  }
  
  @Test
  public void testFindByCoordinateLongitudeAndCoordinateLatitude() {
    //horizon
    List<AkalaShop> horizonShopList = akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(113.591144f, 22.367448f);
    Assert.notEmpty(horizonShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShopDaShiPu().getName().equals(horizonShopList.get(0).getName()));
    
    //Sun Yat-sen University
     
    List<AkalaShop> universityShopList = akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(113.588899f, 22.34799f);
    Assert.notEmpty(universityShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShePing().getName().equals(universityShopList.get(0).getName()));
  }
  

}
