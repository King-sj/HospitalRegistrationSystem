package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
  Flux<User> findByUsername(String username);
  Mono<Boolean> existsByUsername(String username);
}
