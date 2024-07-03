package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserInfoChangeRepository extends ReactiveCrudRepository<UserInfoChange,String> {
  Flux<UserInfoChange> findByUsername(String userName);
  Flux<UserInfoChange> findByUsernameAndDate(String userName, String date);
//  @Query("SELECT * from user u where u.profile->>'username'= :username")
//  Flux<UserInfoChange> findByProfileUsername(String username);;
}
