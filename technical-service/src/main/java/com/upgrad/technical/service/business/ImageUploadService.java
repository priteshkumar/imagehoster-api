package com.upgrad.technical.service.business;

import com.upgrad.technical.service.dao.ImageDao;
import com.upgrad.technical.service.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageUploadService {

  @Autowired
  private ImageDao imageDao;

  @Transactional(propagation = Propagation.REQUIRED)
  public ImageEntity upload(ImageEntity imageEntity) {
    return imageDao.createImage(imageEntity);
  }
}
