package com.upgrad.technical.api.controller;


import com.upgrad.technical.api.model.AuthorizedUserResponse;
import com.upgrad.technical.service.business.AuthenticationService;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.AuthenticationFailedException;
import java.util.Base64;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Write the annotation which adds both the @Controller and @ResponseBody annotation.You do not explicitly need to add @ResponseBody annotation to each mapping method
//Write the annotation here//
//Write the annotation to map with URL request of type '/'
//Write the annotation here//
@RestController
@RequestMapping("/")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  //Write the annotation to map this method with Http request of POST type, map it with URL request of type '/auth/login' and it produces Json
  //Write the annotation here//
  @RequestMapping(method = RequestMethod.POST, path = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorizedUserResponse> login(
      @RequestHeader("authorization") final String authorization)
      throws AuthenticationFailedException {
    //Write code here for above explanation//
    byte[] decodedAuth = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
    String[] authData = new String(decodedAuth).split(":");
    //Call the authenticate() method of AuthenticationService class for authenticateService object and pass email as first argument and password as second argument
    //Receive the value returned by this method in an object of type UserAuthTokenEntity(name it as userAuthToken)
    //Write the code here//
    UserAuthTokenEntity userAuthToken = authenticationService.authenticate(authData[0],
        authData[1]);
    UserEntity user = userAuthToken.getUser();
    AuthorizedUserResponse authorizedUserResponse = new AuthorizedUserResponse()
        .id(UUID.fromString(user.getUuid()))
        .firstName(user.getFirstName()).lastName(user.getLastName()).emailAddress(user.getEmail())
        .mobilePhone(user.getMobilePhone())
        .lastLoginTime(user.getLastLoginAt()).role(user.getRole());
    HttpHeaders headers = new HttpHeaders();
    headers.add("access-token", userAuthToken.getAccessToken());
    return new ResponseEntity<AuthorizedUserResponse>(authorizedUserResponse, headers,
        HttpStatus.OK);
  }
}
