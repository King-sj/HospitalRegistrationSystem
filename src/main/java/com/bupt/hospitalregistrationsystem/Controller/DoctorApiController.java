package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Configuration.InstallNormalService;
import com.bupt.hospitalregistrationsystem.Model.AttendanceInformation;
import com.bupt.hospitalregistrationsystem.Service.MongoAttendanceInformationService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DoctorApiController extends InstallNormalService {
  public DoctorApiController(
          RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService, EmailService emailService,
          MongoAttendanceInformationService mongoAttendanceInformationService
  ) {
    super(redisManager, mongoUserService, mongoUserInfoChangeService, mongoAttendanceInformationService, emailService);
  }
  @PostMapping("/postAttendanceInformation")
  public Mono<AttendanceInformation> postAttendanceInformation(@RequestBody AttendanceInformation attendanceInformation) {
    log.info("received attendance information: {}" , attendanceInformation);
    attendanceInformation.setId(UUID.randomUUID().toString());
    return mongoUserService.findByUsername(attendanceInformation.getDoctorUsername())
                    .singleOrEmpty()
                    .flatMap(doctor-> {
                      attendanceInformation.setDoctor(doctor);
                      return mongoAttendanceInformationService.save(attendanceInformation);
                    });
  }
}
