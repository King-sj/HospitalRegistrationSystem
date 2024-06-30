package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Service.MongoManager;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import com.bupt.hospitalregistrationsystem.Component.EmailService;

import java.util.Random;

@RestController
@RequestMapping("loginSystem")
public class LoginApiController {

  private final EmailService emailService;
  private final RedisManger redisManager;
  private final MongoManager mongoManager;
  private final Logger log;
  @Autowired
  public LoginApiController(EmailService emailService, RedisManger redisManger, MongoManager mongoManager) {
    this.emailService = emailService;
    this.redisManager = redisManger;
    this.mongoManager = mongoManager;
    this.log = LoggerFactory.getLogger(LoginApiController.class);
  }

  @GetMapping
  public Mono<String> index() {
    return Mono.just("welcome to use login System");
  }

  @PostMapping("captcha")
  public Mono<ApiNormResult> captcha(@RequestBody Account account) {
    log.info("receive captcha req : {}" , account.email());
    Random random = new Random(System.currentTimeMillis());
    String verificationCode = String.format("%06d", random.nextInt(1_000_000));

    return redisManager.setValue(account.email() + "-captcha", verificationCode)
            .flatMap(v -> {
              emailService.sendSimpleMessage(account.email(), "captcha", verificationCode);
              var res = new ApiNormResult(true);
              return Mono.just(res);
            })
            .onErrorResume(e->{
              var res = new ApiNormResult(false);
              log.error(e.getMessage());
              return Mono.just(res);
            });
  }
  @PostMapping("signup")
  public Mono<ApiResult> signup(@RequestBody Account account) {
    log.info("receive signup req : email :{} psw:{} captcha:{}" , account.email(), account.password(), account.captcha());
    return redisManager.getValue(account.email()+"-captcha")
            .flatMap(v->{
              if (v.equals(account.captcha())) {
                return mongoManager.existsByUsername(account.email())
                        .flatMap(exs->{
                          if(exs) {
                            return Mono.just(new ApiResult(false, "User already exists"));
                          } else{
                            return mongoManager.save(new User("1", account.email(), account.password()))
                                    .thenReturn(new ApiResult(true, "suc"));
                          }
                        });

              } else {
                return Mono.just(new ApiResult(false, "captcha error"));
              }
            });
  }
}
