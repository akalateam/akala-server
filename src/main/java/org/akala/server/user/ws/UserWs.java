package org.akala.server.user.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.akala.server.user.service.AkalaUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/")
@Component
public class UserWs {

  @Autowired
  private AkalaUserDetailsManager akalaUserDetailsManager;

  @GET
  @Path("/authUser")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean authUser(@QueryParam("userKey") String userKey,
      @QueryParam("userType") String userType, @QueryParam("password") String password) {
    return akalaUserDetailsManager.authUser(userKey, userType, password);
  }
}
