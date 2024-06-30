package com.bupt.hospitalregistrationsystem.Service;

import com.bupt.hospitalregistrationsystem.Model.User;
import com.bupt.hospitalregistrationsystem.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoUserService {
  private final UserRepository userRepository;
  @Autowired
  public MongoUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public Mono<User> save(User user) {
    return userRepository.save(user);
  }
  public Mono<User> findById(String id) {
    return userRepository.findById(id);
  }
  public Flux<User> findAll() {
    return userRepository.findAll();
  }
  public Mono<Void> deleteById(String id) {
    return userRepository.deleteById(id);
  }
  public Mono<Void> deleteAll() {
    return userRepository.deleteAll();
  }
  public Mono<User> update(String id, User newUser) {
    return deleteById(id)
            .then(userRepository.save(newUser));
  }
  public Mono<Boolean> existsById(String id) {
    return userRepository.existsById(id);
  }
  public Flux<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
  public Mono<Boolean> existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }
}
