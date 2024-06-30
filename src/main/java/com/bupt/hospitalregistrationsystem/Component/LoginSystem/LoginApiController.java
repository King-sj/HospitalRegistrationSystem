package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Service.MongoUserService;
import com.bupt.hospitalregistrationsystem.Service.RedisManger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import com.bupt.hospitalregistrationsystem.Component.EmailService;

import java.security.SecureRandom;
import java.util.Random;


@RestController
@RequestMapping("loginSystem")
public class LoginApiController {

  private final EmailService emailService;
  private final RedisManger redisManager;
  private final MongoUserService mongoUserService;
  private final Logger log;
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  @Autowired
  public LoginApiController(EmailService emailService, RedisManger redisManger, MongoUserService mongoUserService) {
    this.emailService = emailService;
    this.redisManager = redisManger;
    this.mongoUserService = mongoUserService;
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
                return mongoUserService.existsByUsername(account.email())
                        .flatMap(exs->{
                          if(exs) {
                            return Mono.just(new ApiResult(false, "User already exists",""));
                          } else{
                            return mongoUserService.save(new User(account.email(), account.email(), account.password()))
                                    .thenReturn(new ApiResult(true, "suc",""));
                          }
                        });

              } else {
                return Mono.just(new ApiResult(false, "captcha error",""));
              }
            });
  }

  /**
   *
   * @param account
   * @return
   * @warn 未删除之前的token
   */
  @PostMapping("login")
  public Mono<ApiResult> login(@RequestBody Account account) {
    log.info("receive login req : {}" , account.email());
    return mongoUserService.findByUsername(account.email())
            .singleOrEmpty()
            .flatMap(user -> user.getPassword().equals(account.password())
                    ? generateToken(32)
                      .flatMap(token -> redisManager.addUserToken(account.email(), token)
                      .thenReturn(new ApiResult(true, "success",token)))
                    : Mono.just(new ApiResult(false, "wrong password","")
            )
    );
  }
  public Mono<String> generateToken(int length) {
    SecureRandom random = new SecureRandom();
    StringBuilder token = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(CHARACTERS.length());
      char randomChar = CHARACTERS.charAt(randomIndex);
      token.append(randomChar);
    }
    return redisManager.exists(token.toString())
            .flatMap(exs->{
              if(exs) {
                return generateToken(length);
              }
              return Mono.just(token.toString()) ;
            });
  }

  @PostMapping("getUser")
  public Mono<User> getUser(@RequestBody Account account) {
    log.info("receive getUser req: {}", account);
    // 从Redis获取电子邮件地址
    return redisManager.getValue(account.token())
            .doOnNext(email -> log.debug("Retrieved email from Redis: {}", email))
            .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
            .flatMap(email -> mongoUserService.findByUsername(email).singleOrEmpty())
            .doOnError(e -> log.error("Error fetching user from MongoDB", e))
            .doOnSuccess(user -> log.debug("User fetched successfully: {}", user));
  }
  @PostMapping("updateUserReq")
  public Mono<User> updateUserReq(@RequestBody User newUser) {
    log.info("receive updateUser req: {}", newUser);
    return mongoUserService.findByUsername(newUser.getEmail())
            .singleOrEmpty()
            .flatMap(user-> {
              log.debug("get user from mongo : {}", user);
              if (user.getPassword().equals(newUser.getPassword())) {
                // TODO(SJ): 添加进信息修改申请表
                return Mono.just(user);
              }
              return Mono.just(user);
            });

  }
}
