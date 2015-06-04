package org.akala.server.shop.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.akala.server.AkalaApplicationTests;
import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.service.AkalaImageService;
import org.akala.server.shop.service.AkalaShopService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@Ignore
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
      shePing.setPhotoId(akalaImageService.storeImage(new FileInputStream("E:/workspace/images/sheping.jpg"), "ShePing", "image/png"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(shePing);
    
    AkalaShop daShiPu = AkalaShopFactory.genAkalaShopDaShiPu();
    try {
      daShiPu.setPhotoId(akalaImageService.storeImage(new FileInputStream("E:/workspace/images/dashipu.jpg"), "DaShiPu", "image/png"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(daShiPu);
    
    AkalaShop heJi = AkalaShopFactory.genAkalaShopHeJi();
    try {
      heJi.setPhotoId(akalaImageService.storeImage(new FileInputStream("E:/workspace/images/heji.jpg"), "HeJi", "image/png"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    akalaShopRepository.save(heJi);
  }
  
  @Test
  public void testFindByCoordinateLongitudeAndCoordinateLatitude() {
    //horizon
//    List<AkalaShop> horizonShopList = akalaShopRepository.findByCoordinateLongitudeAndCoordinateLatitude(113.591144f, 22.367448f);
    List<AkalaShop> horizonShopList = akalaShopService.findAkalaShopList(113.591144f, 22.367448f);
    Assert.notEmpty(horizonShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShopDaShiPu().getName().equals(horizonShopList.get(0).getName()));
    
    //Sun Yat-sen University
//    Point point = new Point(113.588899f, 22.34799f); 
//    List<AkalaShop> universityShopList = akalaShopRepository.findByCoordinateNear(point);
    List<AkalaShop> universityShopList = akalaShopService.findAkalaShopList(113.588899f, 22.34799f);
    Assert.notEmpty(universityShopList);
    Assert.isTrue(AkalaShopFactory.genAkalaShePing().getName().equals(universityShopList.get(0).getName()));
  }
  

}
