package com.bupt.hospitalregistrationsystem.Service;

import com.bupt.hospitalregistrationsystem.Model.UserInfoChange;
import com.bupt.hospitalregistrationsystem.Model.UserInfoChangeRepository;
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

  @Autowired
  public MongoUserInfoChangeService(UserInfoChangeRepository userInfoChangeRepository) {
    this.userInfoChangeRepository = userInfoChangeRepository;
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
    BasicDBObject subQuery = new BasicDBObject();
    Criteria queryCriteria = Criteria.where("username").is(info.getUsername())
            .and("Date").is(info.getDate());
    Query query = Query.query(queryCriteria);
    return this.userInfoChangeRepository.select(query, ScanOptions.NONE)
            .singleOrEmpty()
            .flatMap(v->{
              return Mono.just(true);
            });
  }
}
