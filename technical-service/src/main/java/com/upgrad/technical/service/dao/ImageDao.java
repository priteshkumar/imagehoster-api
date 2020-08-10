package com.upgrad.technical.service.dao;

import com.upgrad.technical.service.entity.ImageEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao {


  @PersistenceContext
  private EntityManager entityManager;

  public ImageEntity createImage(ImageEntity imageEntity) {
    entityManager.persist(imageEntity);
    return imageEntity;
  }

}
