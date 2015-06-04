package org.akala.server.user.ws;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.akala.server.user.bean.AddressInfo;
import org.akala.server.user.bean.SecurityUserDetails;
import org.akala.server.user.service.AkalaAddressService;
import org.akala.server.user.service.AkalaMobileService;
import org.akala.server.user.service.AkalaUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Path("/")
@Component
public class UserWs {

  @Autowired
  private AkalaUserDetailsManager akalaUserDetailsManager;

  @Resource(name = "akalaMobileService")
  private AkalaMobileService akalaMobileService;

  @Resource(name = "akalaAddressService")
  private AkalaAddressService akalaAddressService;

  @GET
  @Path("/authUser")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean authUser(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType, @QueryParam("password") String password) {
    return akalaUserDetailsManager.authUser(userKey, userType, password);
  }

  @POST
  @Path("/signupUser")
  @Produces(MediaType.APPLICATION_FORM_URLENCODED)
  public void signupUser(@FormParam("userKey") String userKey,
      @FormParam("userType") String userType, @FormParam("password") String password,
      @FormParam("credentials") String credentials) {
    if (SecurityUserDetails.USER_KEY_TYPE_PHONE.equals(userType)) {
      boolean credentialsValid = akalaMobileService.validMobileCredentials(userKey, credentials);
      Assert.isTrue(credentialsValid, "Credentials is wrong");
    }

    UserDetails user =
        new SecurityUserDetails(userKey + SecurityUserDetails.USER_KEY_TYPE_SEP + userType,
            password, new ArrayList<GrantedAuthority>());
    akalaUserDetailsManager.createUser(user);
  }

  @GET
  @Path("/chekUserExist")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean chekUserExist(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType) {
    return akalaUserDetailsManager.userExists(userKey + SecurityUserDetails.USER_KEY_TYPE_SEP
        + userType);
  }

  @POST
  @Path("/credentials")
  @Produces(MediaType.APPLICATION_FORM_URLENCODED)
  public String getMobileCredentials(@FormParam("mobile") String mobile) {
    return akalaMobileService.sendMobileCredentials(mobile);
  }

  @POST
  @Path("/resetPwd")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean resetPwd(@FormParam("userKey") String userKey,
      @FormParam("userType") String userType) {
    return akalaUserDetailsManager.resetPwd(userKey, userType);
  }

  @POST
  @Path("/saveAddress")
  @Produces(MediaType.APPLICATION_JSON)
  public void saveAddress(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType, AddressInfo addressInfo) {
    akalaAddressService.saveAddress(userKey, userType, addressInfo);
  }

  @GET
  @Path("/retrieveAddress")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AddressInfo> retrieveAddress(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType) {
    return akalaAddressService.getAddress(userKey, userType);
  }

  @GET
  @Path("/deleteAddress")
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteAddress(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType, @QueryParam("addressId") String addressId) {
    akalaAddressService.deleteAddress(userKey, userType, addressId);
  }
}
