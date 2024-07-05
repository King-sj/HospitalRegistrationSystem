package com.bupt.hospitalregistrationsystem.Controler;

import com.bupt.hospitalregistrationsystem.Component.EmailService;
import com.bupt.hospitalregistrationsystem.Configuration.InstallNormalService;
import com.bupt.hospitalregistrationsystem.Model.Admin;
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

@RestController
@RequestMapping("api")
public class AdminApiController extends InstallNormalService {
  public AdminApiController(RedisManger redisManager, MongoUserService mongoUserService, MongoUserInfoChangeService mongoUserInfoChangeService, MongoAttendanceInformationService mongoAttendanceInformationService, EmailService emailService) {
    super(redisManager, mongoUserService, mongoUserInfoChangeService, mongoAttendanceInformationService, emailService);
  }

  @PostMapping("checkAttendanceInfo")
  public Mono<Boolean> checkAttendanceInfo(@RequestBody AttendanceInformation attendanceInformation) {
    log.info("received request to checkAttendanceInfo, {}", attendanceInformation);
    // 首先通过ID查找考勤信息
    return mongoAttendanceInformationService.findById(attendanceInformation.getId())
            .flatMap(info -> {
              // 更新考勤信息的检查状态和是否通过
              info.setChecked(true);
              info.setPass(attendanceInformation.isPass());
              emailService.sendSimpleMessage(info.getDoctorUsername(),"你的出诊计划审核",
                attendanceInformation.isPass() ? "已通过" : "已驳回"
              );
              // 通过用户名查找审核人
              // 设置审核人
              return mongoUserService.findByUsername(attendanceInformation.getChecker().getUsername())
                      .doOnNext(info::setChecker)
                      .then(mongoAttendanceInformationService.save(info)) // 保存更新后的考勤信息
                      .thenReturn(true); // 返回保存成功标志
            })
            .doOnError(e -> {
              log.info("Error occurred while updating attendance information: {}", e.getMessage());
            })
            .onErrorReturn(false); // 如果发生错误，返回保存失败标志

  }
}
