package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.LoginSystem.LoginApiController;
import com.bupt.hospitalregistrationsystem.Model.Admin;
import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Model.UserInfoChange;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class ApiControler {
  private final RedisManger redisManager;
  private final MongoUserService mongoUserService;
  private final MongoUserInfoChangeService mongoUserInfoChangeService;
  private final Logger log;

  @Autowired
  public ApiControler(
          RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService
  ) {
    this.redisManager = redisManager;
    this.mongoUserService = mongoUserService;
    this.mongoUserInfoChangeService = mongoUserInfoChangeService;
    this.log = LoggerFactory.getLogger(LoginApiController.class);
  }
  @GetMapping("/test")
  public Mono<User> test(){
    var admin = new Admin("0","admin","admin");
    admin.setName("root");
    admin.setIdentity("xxx");
    admin.setAddress("123456");
    admin.setEmail("2175616761@qq.com");
    admin.setPhone("18146295489");
    return mongoUserService.save(admin);
  }

  @GetMapping("/getAllUserInfoChangeReq")
  public Flux<UserInfoChange> getAllUserInfoChangeReq() {
    log.info("receive req getAllUserInfoChangeReq");
    return mongoUserInfoChangeService.findAll();
  }
  @PostMapping("/executeUserInfoChangeReq")
  public Mono<Boolean> executeUserInfoChangeReq(@RequestBody UserInfoChange userInfoChange){
    log.info("receive req executeUserInfoChangeReq, {}", userInfoChange);
    return mongoUserInfoChangeService.executeUserInfoChangeReq(userInfoChange);
  }

}