package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Configuration.InstallNormalService;
import com.bupt.hospitalregistrationsystem.Model.AttendanceInformation;
import com.bupt.hospitalregistrationsystem.Model.MedicalRecordItem;
import com.bupt.hospitalregistrationsystem.Model.Patient;
import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Service.MongoAttendanceInformationService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserInfoChangeService;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import com.bupt.hospitalregistrationsystem.Utils.CurrentTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

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
            .flatMap(item -> mongoUserService.findByUsername(attendanceInformation.getPatients().get(0).getEmail())
                            .singleOrEmpty()
                            .flatMap(patient -> {
                              item.addPatient(patient);
                              item.setCnt(item.getCnt() + 1);
                              return mongoAttendanceInformationService.save(item)
                                      .flatMap(v -> {
                                        var rec = new MedicalRecordItem();
                                        rec.setDoctor(item.getDoctor());
                                        rec.setDate(CurrentTime.getCurrentTime());
                                        rec.setId(item.getId());
                                        patient.addMedicalRecord(rec);
                                        emailService.sendSimpleMessage(patient.getUsername(),"预约结果",item.getStartTime()+"成功");
                                        return mongoUserService.save(patient);
                                      }).thenReturn(true);
                            })
            )
            .onErrorResume(e->{
              emailService.sendSimpleMessage(attendanceInformation.getPatients().get(0).getEmail(),"预约结果","失败");
              return Mono.just(false);
            })
            . defaultIfEmpty(false);
  }
  @PostMapping("/getMedicalRecord")
  public Mono<ArrayList<MedicalRecordItem>> getMedicalRecord(@RequestBody User user) {
    log.info("receive req for getMedicalRecord, {}", user);
    return mongoUserService.findByUsername(user.getEmail())
            .singleOrEmpty()
            .flatMap(u->
               Mono.just(u.getMedicalRecord())
            );
  }
}
