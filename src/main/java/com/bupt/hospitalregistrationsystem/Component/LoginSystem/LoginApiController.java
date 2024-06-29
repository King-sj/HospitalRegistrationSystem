package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import com.bupt.hospitalregistrationsystem.DAO.RedisManger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import com.bupt.hospitalregistrationsystem.Component.EmailService;

import java.util.Random;

@RestController
@RequestMapping("loginSystem")
public class LoginApiController {

  private final EmailService emailService;
  private final RedisManger redisManger;
  @Autowired
  public LoginApiController(EmailService emailService, RedisManger redisManger) {
    this.emailService = emailService;
    this.redisManger = redisManger;
  }

  @GetMapping
  public Mono<String> index() {
    return Mono.just("welcome to use login System");
  }

  @PostMapping("captcha")
  public Mono<ApiNormResult> captcha(@RequestBody Email email) {
    System.out.println("receive captcha req : " + email.email());
    Random random = new Random(System.currentTimeMillis());
    String verificationCode = String.valueOf(random.nextInt(1000000));

    // 使用flatMap代替直接订阅
    return redisManger.setValue(email.email() + "-captcha", verificationCode)
            .flatMap(v -> {
              emailService.sendSimpleMessage(email.email(), "captcha", verificationCode);
              // 这里不应该阻塞，如果需要获取redis中的值，应该使用非阻塞的方式
                // System.out.println("get " + redisManger.getValue("ff").block());
              var res = new ApiNormResult(true);
              return Mono.just(res);
            });
  }
}
