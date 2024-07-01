package com.bupt.hospitalregistrationsystem.Service;

import com.bupt.hospitalregistrationsystem.Model.UserInfoChange;
import com.bupt.hospitalregistrationsystem.Model.UserInfoChangeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoUserInfoChangeService {
  private final UserInfoChangeRespository userInfoChangeRespository;

  @Autowired
  public MongoUserInfoChangeService(UserInfoChangeRespository userInfoChangeRespository) {
    this.userInfoChangeRespository = userInfoChangeRespository;
  }
  public Mono<UserInfoChange> save(UserInfoChange info) {
    return this.userInfoChangeRespository.save(info);
  }
  public Flux<UserInfoChange> findByUsername(String username) {
    return this.userInfoChangeRespository.findByUsername(username);
  }
}
