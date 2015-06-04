package org.akala.server.user.service;

import java.util.List;

import org.akala.server.user.bean.AddressInfo;

public interface AkalaAddressService {
  public void saveAddress(String userKey, String userType, AddressInfo addressInfo);

  public List<AddressInfo> getAddress(String userKey, String userType);

  public void deleteAddress(String userKey, String userType, String addressId);
}
