package org.akala.server.user.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AkalaUser {

  @Id
  private BigInteger id;
  private List<Login> logins;
  private Basic basic;
  private List<AddressInfo> addressInfos;

  public AkalaUser() {
    logins = new ArrayList<Login>();
    addressInfos = new ArrayList<AddressInfo>();
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public List<Login> getLogins() {
    return logins;
  }

  public void setLogins(List<Login> logins) {
    this.logins = logins;
  }

  public Basic getBasic() {
    return basic;
  }

  public void setBasic(Basic basic) {
    this.basic = basic;
  }

  public List<AddressInfo> getAddressInfos() {
    return addressInfos;
  }

  public void setAddressInfos(List<AddressInfo> addressInfos) {
    this.addressInfos = addressInfos;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
