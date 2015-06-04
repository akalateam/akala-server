package org.akala.server.shop.service;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface AkalaImageService {
  
  public String storeImage(InputStream imageStream, String fileName, String contentType);
  
  public GridFSDBFile getImageById(String id);

}
