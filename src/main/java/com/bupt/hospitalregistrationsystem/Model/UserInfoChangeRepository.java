package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import org.springframework.data.mongodb.core.query.Query;

public interface UserInfoChangeRepository extends ReactiveCrudRepository<UserInfoChange,String> {
  Flux<UserInfoChange> findByUsername(String userName);
  Flux<UserInfoChange> select(Query query, ScanOptions scanOptions);
}
