package org.akala.server.shop.ws;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.akala.server.shop.bean.AkalaShop;
import org.akala.server.shop.bean.AkalaShopItem;
import org.akala.server.shop.service.AkalaImageService;
import org.akala.server.shop.service.AkalaShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongodb.gridfs.GridFSDBFile;

@Path("/")
@Component
public class ShopWs {
  
  @Autowired
  private AkalaShopService akalaShopService;
  
  @Autowired
  private AkalaImageService akalaImageService;
  
  @GET
  @Path("/shopList")
  @Produces(MediaType.APPLICATION_JSON)
  public List<GeoResult<AkalaShop>> getAkalaShops(@QueryParam("longitude") float longitude, @QueryParam("latitude") float latitude) {
    return akalaShopService.findAkalaShopList(longitude, latitude);
  }

  @GET
  @Path("/shop/{shopId}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AkalaShopItem> getAkalaShopItems(@PathVariable BigInteger shopId) {
    return akalaShopService.findAkalaShopItemListByShopId(shopId);
  }
  
  @GET
  @Path("/image/{imageId}")
  @Produces(MediaType.APPLICATION_JSON)
  public void getAkalaImage(@PathParam(value = "imageId") String imageId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
    GridFSDBFile gridFSDBFile =  akalaImageService.getImageById(imageId);
    response.setCharacterEncoding("utf-8");
    response.setContentType("multipart/form-data");
    response.setHeader("Content-Disposition", "attachment;fileName="
            + gridFSDBFile.getFilename());
    try {
      gridFSDBFile.writeTo(response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
