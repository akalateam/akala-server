package org.akala.server.shop.repository;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShop.BussinessHour;
import org.akala.server.shop.bean.AkalaShop.Coordinate;

public class AkalaShopFactory {
  
  public static AkalaShop genAkalaShopDaShiPu() {
    AkalaShop daShiPu = new AkalaShop();
    daShiPu.setName("大石浦渔村");
    daShiPu.setAddr("珠海市香洲区唐家湾渔村");
    daShiPu.setDesc("有景观位，是老字号，有露天位，可以刷卡，有包厢，有停车位，有服务费，提供在线菜单，区分烟区。");
    daShiPu.setPhone("0756-3312288");
    daShiPu.setBussinessHour(new BussinessHour(9, 30, 22, 0));
    daShiPu.setCoordinate(new Coordinate(113.592507f, 22.36843f));
    daShiPu.setAvgDeliveryMinutes(40);
    daShiPu.setDeliveryFee(5);
    daShiPu.setMinDeliveryAmount(50);
    return daShiPu;
  }
  
  public static AkalaShop genAkalaShopHeJi() {
    AkalaShop heJi = new AkalaShop();
    heJi.setName("和记菜馆");
    heJi.setAddr("珠海市香洲区唐家湾茶水井");
    heJi.setDesc("餐馆介绍位置比较偏的一间农庄私房菜，味道是非常的好，生意太火位置难订，建议用餐提前预定。充满农家风情，供应的都是农家菜品，推荐菜是焖鸭、芋头煲、陈皮骨。");
    heJi.setPhone("0756-3313046");
    heJi.setBussinessHour(new BussinessHour(10, 30, 19, 30));
    heJi.setCoordinate(new Coordinate(113.58859f, 22.359362f));
    heJi.setAvgDeliveryMinutes(90);
    heJi.setDeliveryFee(20);
    heJi.setMinDeliveryAmount(200);
    return heJi;
  }
  
  public static AkalaShop genAkalaShePing() {
    AkalaShop shePing = new AkalaShop();
    shePing.setName("蛇平饭店(唐家店)");
    shePing.setAddr("香洲区 唐家湾明园路34号之一(近中山大学)");
    shePing.setDesc("明明吃的是“海鲜”，却要叫做“蛇平”；明明就是个“大排档”，却要号称“饭店”。不管如何，海鲜还是挺“新鲜”的。招牌杂鱼煲，呈盘“略显失望”，味道仍“不失水准”。价位“适中”，交通也“方便”，值得一试。");
    shePing.setPhone("0756-3312138");
    shePing.setBussinessHour(new BussinessHour(10, 30, 22, 00));
    shePing.setCoordinate(new Coordinate(113.593204f, 22.351414f));
    shePing.setAvgDeliveryMinutes(30);
    shePing.setDeliveryFee(0);
    shePing.setMinDeliveryAmount(50);
    return shePing;
  }

}
