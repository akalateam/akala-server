package org.akala.server.user.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.akala.server.user.bean.AddressInfo;
import org.akala.server.user.bean.AkalaUser;
import org.akala.server.user.repository.AddressInfoRepository;
import org.akala.server.user.repository.AkalaUserRepository;
import org.akala.server.user.service.AkalaAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service("akalaAddressService")
public class AkalaAddressServiceImpl implements AkalaAddressService {

  @Resource(name = "akalaUserRepository")
  private AkalaUserRepository akalaUserRepository;

  @Resource(name = "addressInfoRepository")
  private AddressInfoRepository addressInfoRepository;

  @Override
  public void saveAddress(String userKey, String userType, AddressInfo addressInfo) {
    AkalaUser user = akalaUserRepository.findOneByLoginsKeyAndLoginsType(userKey, userType);
    if (user.getAddressInfos() == null) {
      user.setAddressInfos(new ArrayList<AddressInfo>());
    }
    if (StringUtils.isEmpty(addressInfo.getId())) {
      addressInfo.setId(null);
      addressInfoRepository.save(addressInfo);
      user.getAddressInfos().add(addressInfo);
    } else {
      for (AddressInfo tempAddr : user.getAddressInfos()) {
        if (tempAddr.getId().equals(addressInfo.getId())) {
          BeanUtils.copyProperties(addressInfo, tempAddr);
          addressInfoRepository.save(addressInfo);
        }
      }
    }
    akalaUserRepository.save(user);
  }

  @Override
  public List<AddressInfo> getAddress(String userKey, String userType) {
    AkalaUser user = akalaUserRepository.findOneByLoginsKeyAndLoginsType(userKey, userType);
    return user.getAddressInfos();
  }

  @Override
  public void deleteAddress(String userKey, String userType, String addressId) {
    AkalaUser user = akalaUserRepository.findOneByLoginsKeyAndLoginsType(userKey, userType);
    for (Iterator<AddressInfo> addrIter = user.getAddressInfos().iterator(); addrIter.hasNext();) {
      AddressInfo tempAddr = addrIter.next();
      if (tempAddr.getId().equals(addressId)) {
        addrIter.remove();
      }
    }
    akalaUserRepository.save(user);
    addressInfoRepository.delete(addressId);
  }
}
