package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserInfoChangeRespository extends ReactiveCrudRepository<UserInfoChange,String> {
  Flux<UserInfoChange> findByUsername(String userName);
}
