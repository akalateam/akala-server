package org.akala.server.shop.bean;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AkalaShopItem {

  @Id
  private BigInteger id;
  private BigInteger eateryId;
  private float amount;
  private String name;
  private String desc;
  private String photoId;
  private String status;
  private String category;

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

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

  public String getPhotoId() {
    return photoId;
  }
  
  public void setPhotoId(String photoId) {
    this.photoId = photoId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigInteger getEateryId() {
    return eateryId;
  }
  
  public void setEateryId(BigInteger eateryId) {
    this.eateryId = eateryId;
  }
  
  public String getCategory() {
    return category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }

}
