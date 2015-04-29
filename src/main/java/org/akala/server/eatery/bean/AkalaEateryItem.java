package org.akala.server.eatery.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class AkalaEateryItem {

  @Id
  private BigInteger id;
  @DBRef
  private AkalaEatery eatery;
  private float amount;
  private String name;
  private String desc;
  private String fileId;
  private String status;
  private List<AkalaIngredient> ingredients;
  
  public AkalaEateryItem() {
    setIngredients(new ArrayList<AkalaIngredient>());
  }

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

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<AkalaIngredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<AkalaIngredient> ingredients) {
    this.ingredients = ingredients;
  }


}
