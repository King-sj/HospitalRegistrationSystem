package com.bupt.hospitalregistrationsystem.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/user")
public class ApiController {

  @GetMapping("/test")
  public Mono<String> test()
  {
    return Mono.just("test suc");
  }
}