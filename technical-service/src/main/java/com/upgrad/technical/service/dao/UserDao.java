package com.upgrad.technical.service.dao;

import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

  //Write the annotation which handles entities holding data which are to be persisted in some persistence store like a database.
  @PersistenceContext
  private EntityManager entityManager;

  public UserEntity createUser(UserEntity userEntity) {
    entityManager.persist(userEntity);
    return userEntity;
  }

  public UserEntity getUserByEmail(String email) {
    try {
      return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email",
          email).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public void updateUser(UserEntity userEntity) {
    entityManager.merge(userEntity);
  }

  public UserAuthTokenEntity createAuthToken(UserAuthTokenEntity userAuthTokenEntity) {
    entityManager.persist(userAuthTokenEntity);
    return userAuthTokenEntity;
  }
}