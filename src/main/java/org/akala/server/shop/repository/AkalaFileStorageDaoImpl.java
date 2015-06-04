package org.akala.server.shop.repository;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Repository("akalaFileStorageDao")
public class AkalaFileStorageDaoImpl implements AkalaFileStorageDao {
  
  @Autowired
  private GridFsTemplate gridFsTemplate;

  public String store(InputStream inputStream, String fileName, String contentType,
      DBObject metaData) {
    GridFSFile gridFSFile = this.gridFsTemplate.store(inputStream, fileName, contentType, metaData);
    if (gridFSFile != null) {
      return gridFSFile.getId().toString();
    }

    return null;
  }

  public GridFSDBFile getById(String id) {
    return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
  }

}
