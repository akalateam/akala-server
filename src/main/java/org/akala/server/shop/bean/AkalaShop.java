package org.akala.server.shop.bean;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AkalaShop {

  @Id
  private BigInteger id;
  private String name;
  private String desc;
  private String addr;
  private Coordinate coordinate;
  private String phone;
  private String photoId;
  private BussinessHour bussinessHour;
  private int avgDeliveryMinutes;
  private int minDeliveryAmount;
  private int deliveryFee;
  private String[] payMethod;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }
  
  public Coordinate getCoordinate() {
    return coordinate;
  }
  
  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhotoId() {
    return photoId;
  }

  public void setPhotoId(String photoId) {
    this.photoId = photoId;
  }

  public BussinessHour getBussinessHour() {
    return bussinessHour;
  }

  public void setBussinessHour(BussinessHour bussinessHour) {
    this.bussinessHour = bussinessHour;
  }

  public int getAvgDeliveryMinutes() {
    return avgDeliveryMinutes;
  }

  public void setAvgDeliveryMinutes(int avgDeliveryMinutes) {
    this.avgDeliveryMinutes = avgDeliveryMinutes;
  }

  public int getMinDeliveryAmount() {
    return minDeliveryAmount;
  }

  public void setMinDeliveryAmount(int minDeliveryAmount) {
    this.minDeliveryAmount = minDeliveryAmount;
  }

  public int getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(int deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public String[] getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(String[] payMethod) {
    this.payMethod = payMethod;
  }
  
  public static class Coordinate {
    private float longitude;
    private float latitude;
    
    public Coordinate(float longitude, float latitude) {
      this.longitude = longitude;
      this.latitude = latitude;
    }
    
    public float getLatitude() {
      return latitude;
    }
    
    public void setLatitude(float latitude) {
      this.latitude = latitude;
    }
    
    public float getLongitude() {
      return longitude;
    }
    
    public void setLongitude(float longitude) {
      this.longitude = longitude;
    }
  }

  public static class BussinessHour {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    
    public BussinessHour(int startHour, int startMinute, int endHour, int endMinute) {
      this.startHour = startHour;
      this.startMinute = startMinute;
      this.endHour = endHour;
      this.endMinute = endMinute;
    }

    public int getStartHour() {
      return startHour;
    }

    public void setStartHour(int startHour) {
      this.startHour = startHour;
    }

    public int getStartMinute() {
      return startMinute;
    }

    public void setStartMinute(int startMinute) {
      this.startMinute = startMinute;
    }

    public int getEndHour() {
      return endHour;
    }

    public void setEndHour(int endHour) {
      this.endHour = endHour;
    }

    public int getEndMinute() {
      return endMinute;
    }

    public void setEndMinute(int endMinute) {
      this.endMinute = endMinute;
    }
  }

}
