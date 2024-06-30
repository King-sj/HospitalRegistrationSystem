package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Model.Admin;
import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/user")
public class ApiControler {
  private final RedisManger redisManager;
  private final MongoUserService mongoUserService;
  @Autowired
  public ApiControler(RedisManger redisManager, MongoUserService mongoUserService) {
    this.redisManager = redisManager;
    this.mongoUserService = mongoUserService;
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
}