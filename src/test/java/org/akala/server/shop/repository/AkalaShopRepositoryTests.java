package org.akala.server.shop.repository;

import java.io.IOException;
import java.util.List;

import org.akala.server.AkalaApplicationTests;
import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.service.AkalaImageService;
import org.akala.server.shop.service.AkalaShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.geo.GeoResult;
import org.springframework.util.Assert;

public class AkalaShopRepositoryTests extends AkalaApplicationTests {

  @Autowired
  private AkalaShopRepository akalaShopRepository;

  @Autowired
  private AkalaShopService akalaShopService;

  @Autowired
  private AkalaImageService akalaImageService;

  @Test
  public void testSave() {
    AkalaShop shePing = AkalaShopFactory.genAkalaShePing();
    try {
      shePing.setPhotoId(akalaImageService.storeImage(
          (new ClassPathResource("images/蛇平.jpg")).getInputStream(), "ShePing", "image/png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(shePing);

    AkalaShop daShiPu = AkalaShopFactory.genAkalaShopDaShiPu();
    try {
      daShiPu.setPhotoId(akalaImageService.storeImage(
          (new ClassPathResource("images/大石浦.jpg")).getInputStream(), "DaShiPu", "image/png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(daShiPu);

    AkalaShop heJi = AkalaShopFactory.genAkalaShopHeJi();
    try {
      heJi.setPhotoId(akalaImageService.storeImage(
          (new ClassPathResource("images/和记.jpg")).getInputStream(), "HeJi", "image/png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(heJi);
  }

  @Test
  public void testFindByCoordinateLongitudeAndCoordinateLatitude() {
    //horizon
//    List<AkalaShop> horizonShopList = akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(113.591144f, 22.367448f);
    List<GeoResult<AkalaShop>> horizonShopList = akalaShopService.findAkalaShopList(113.591144f, 22.367448f);
    Assert.notEmpty(horizonShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShopDaShiPu().getName().equals(horizonShopList.get(0).getContent().getName()));
    
    //Sun Yat-sen University
//    Point point = new Point(113.588899f, 22.34799f); 
//    List<AkalaShop> universityShopList = akalaShopRepository.findByCoordinateNear(point);
    List<GeoResult<AkalaShop>> universityShopList = akalaShopService.findAkalaShopList(113.588899f, 22.34799f);
    Assert.notEmpty(universityShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShePing().getName().equals(universityShopList.get(0).getContent().getName()));
  }
  

}
