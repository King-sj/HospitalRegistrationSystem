package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Configuration.InstallNormalService;
import com.bupt.hospitalregistrationsystem.Model.AttendanceInformation;
import com.bupt.hospitalregistrationsystem.Model.Patient;
import com.bupt.hospitalregistrationsystem.Service.MongoAttendanceInformationService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class PatientApiController extends InstallNormalService {
  public PatientApiController(
          RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService, EmailService emailService,
          MongoAttendanceInformationService mongoAttendanceInformationService
  ) {
    super(
            redisManager, mongoUserService, mongoUserInfoChangeService,
            mongoAttendanceInformationService, emailService
    );
  }

  @PostMapping("bookDoctor")
  public Mono<Boolean> bookDoctor(@RequestBody AttendanceInformation attendanceInformation) {
    log.info("receive req for bookDoctor, {}", attendanceInformation);
    return mongoAttendanceInformationService
            .findById(attendanceInformation.getId())
            .flatMap(Mono::just)
            .filter(item -> item.getCnt() < item.getMaxCnt())
            .flatMap(item -> mongoUserService.findByUsername(attendanceInformation.getPatients().get(0).getUsername())
                    .singleOrEmpty()
                    .flatMap(patient -> {
                      item.addPatient((Patient) patient);
                      item.setCnt(item.getCnt() + 1);
                      return mongoAttendanceInformationService.save(item)
                              .thenReturn(true);
                    })
            )
            .defaultIfEmpty(false);
  }
}
