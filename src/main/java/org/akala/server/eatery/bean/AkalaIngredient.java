package org.akala.server.eatery.bean;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AkalaIngredient {

  @Id
  private BigInteger id;
  private String name;
  private String desc;
  private String efficacy;

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

  public String getEfficacy() {
    return efficacy;
  }

  public void setEfficacy(String efficacy) {
    this.efficacy = efficacy;
  }


}
