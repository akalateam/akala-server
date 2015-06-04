package org.akala.server.shop.repository;

import java.io.InputStream;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface AkalaFileStorageDao {
  
  public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData);
  
  public GridFSDBFile getById(String id);

}
