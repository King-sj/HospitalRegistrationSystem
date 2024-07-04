package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Component.LoginSystem.LoginApiController;
import com.bupt.hospitalregistrationsystem.Configuration.InstallNormalService;
import com.bupt.hospitalregistrationsystem.Model.Admin;
import com.bupt.hospitalregistrationsystem.Model.AttendanceInformation;
import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Model.UserInfoChange;
import com.bupt.hospitalregistrationsystem.Service.MongoAttendanceInformationService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class ApiController extends InstallNormalService {

  @Autowired
  public ApiController(
          RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService,
          EmailService emailService, MongoAttendanceInformationService mongoAttendanceInformationService
  ) {
    super(redisManager, mongoUserService, mongoUserInfoChangeService, mongoAttendanceInformationService, emailService);
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

  @PostMapping("getAttendanceInformation")
  public Flux<AttendanceInformation> getAttendanceInformation(@RequestBody AttendanceInformation attendanceInformation){
    log.info("receive req getAttendanceInformation, {}", attendanceInformation);
    if (attendanceInformation.getDoctorUsername().isEmpty())
      return mongoAttendanceInformationService.findAll();
    else
      return mongoAttendanceInformationService
              .findByDoctorUsername(attendanceInformation.getDoctorUsername());
  }
}