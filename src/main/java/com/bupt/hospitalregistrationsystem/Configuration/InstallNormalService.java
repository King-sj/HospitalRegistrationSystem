package com.bupt.hospitalregistrationsystem.Configuration;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Component.LoginSystem.LoginApiController;
import com.bupt.hospitalregistrationsystem.Service.MongoAttendanceInformationService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InstallNormalService {
  protected final RedisManger redisManager;
  protected final MongoUserService mongoUserService;
  protected final MongoUserInfoChangeService mongoUserInfoChangeService;
  protected final MongoAttendanceInformationService mongoAttendanceInformationService;
  protected final EmailService emailService;
  protected final Logger log;
  public InstallNormalService(
          RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService,
          MongoAttendanceInformationService mongoAttendanceInformationService, EmailService emailService

  ) {
    this.redisManager = redisManager;
    this.mongoUserService = mongoUserService;
    this.mongoUserInfoChangeService = mongoUserInfoChangeService;
    this.emailService = emailService;
    this.mongoAttendanceInformationService = mongoAttendanceInformationService;
    this.log = LoggerFactory.getLogger(LoginApiController.class);
  }
}
