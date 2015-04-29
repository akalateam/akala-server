package org.akala.server.eatery.bean;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AkalaEatery {

  @Id
  private BigInteger id;
  private String name;
  private String desc;
  private String addr;
  private String phone;
  private String fileId;

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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

}
