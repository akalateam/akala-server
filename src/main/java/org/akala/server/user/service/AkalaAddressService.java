package org.akala.server.user.service;

import org.akala.server.user.bean.AddressInfo;

public interface AkalaAddressService {
  public void saveAddress(String userKey, String userType, AddressInfo addressInfo);
}
