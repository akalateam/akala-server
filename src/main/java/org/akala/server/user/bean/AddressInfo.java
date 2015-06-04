package org.akala.server.user.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddressInfo {

  @Id
  private String id;
  private String name;
  private String gender;
  private LocationInfo location;
  private String detailLocation;
  private String mobile;

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocationInfo getLocation() {
    return location;
  }

  public void setLocation(LocationInfo location) {
    this.location = location;
  }

  public String getDetailLocation() {
    return detailLocation;
  }

  public void setDetailLocation(String detailLocation) {
    this.detailLocation = detailLocation;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public static class LocationInfo {
    private String pguid;
    private String name;
    private String lng;
    private String lat;

    public String getPguid() {
      return pguid;
    }

    public void setPguid(String pguid) {
      this.pguid = pguid;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getLng() {
      return lng;
    }

    public void setLng(String lng) {
      this.lng = lng;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

  }
}
