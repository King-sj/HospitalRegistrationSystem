package com.bupt.hospitalregistrationsystem.Service;

import com.bupt.hospitalregistrationsystem.Model.UserInfoChange;
import com.bupt.hospitalregistrationsystem.Model.UserInfoChangeRepository;
import com.bupt.hospitalregistrationsystem.Model.UserRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoUserInfoChangeService {
  private final UserInfoChangeRepository userInfoChangeRepository;
  private final UserRepository userRepository;

  @Autowired
  public MongoUserInfoChangeService(
          UserInfoChangeRepository userInfoChangeRepository,
          UserRepository userRepository
  ) {
    this.userInfoChangeRepository = userInfoChangeRepository;
    this.userRepository = userRepository;
  }
  public Mono<UserInfoChange> save(UserInfoChange info) {
    return this.userInfoChangeRepository.save(info);
  }
  public Flux<UserInfoChange> findByUsername(String username) {
    return this.userInfoChangeRepository.findByUsername(username);
  }
  public Flux<UserInfoChange> findAll() {
    return this.userInfoChangeRepository.findAll();
  }

  public Mono<Boolean> executeUserInfoChangeReq(UserInfoChange info) {
   return userInfoChangeRepository.findByUsernameAndDate(info.getUsername(),info.getDate())
           .singleOrEmpty()
           .flatMap(uic->{
             uic.setChecked(true);
             uic.setAllowed(info.getAllowed());
             uic.setChecker(info.getChecker());  // TODO: client发过来的信息不全，需要从数据库查找替代
             var newUser = uic.getNewUserInfo();
             newUser.setId(uic.getOldUserInfo().getId());
             return userInfoChangeRepository.save(uic)
                     .flatMap(savedUic -> {
                       if (uic.getAllowed()) {
                         return userRepository.save(newUser)
                                 .then(Mono.just(true));
                       } else {
                         return Mono.just(false);
                       }
                     });
           });
  }
}
