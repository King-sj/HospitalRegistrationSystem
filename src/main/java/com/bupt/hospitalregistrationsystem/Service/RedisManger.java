package com.bupt.hospitalregistrationsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
public class RedisManger {

  private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
  @Autowired
  public RedisManger(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
    this.reactiveRedisTemplate = reactiveRedisTemplate;
  }

  public Mono<Boolean> setValue(String key, String value) {
    return reactiveRedisTemplate.opsForValue().set(key, value, Duration.ofSeconds(60));
  }

  public Mono<String> getValue(String key) {
    return reactiveRedisTemplate.opsForValue().get(key);
  }

}
