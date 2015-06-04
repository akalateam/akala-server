package org.akala.server.shop.service.impl;

import java.io.InputStream;

import org.akala.server.shop.repository.AkalaFileStorageDao;
import org.akala.server.shop.service.AkalaImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service("akalaImageService")
public class AkalaImageServiceImpl implements AkalaImageService {

  @Autowired
  private AkalaFileStorageDao akalaFileStorageDao;
  
  public String storeImage(InputStream imageStream, String fileName, String contentType) {
    DBObject metaData = new BasicDBObject();    
    return akalaFileStorageDao.store(imageStream, fileName, contentType, metaData);
  }

  public GridFSDBFile getImageById(String id) {
    return akalaFileStorageDao.getById(id);
  } 

}
