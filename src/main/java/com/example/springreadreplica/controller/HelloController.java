package com.example.springreadreplica.controller;

import com.example.springreadreplica.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @Autowired
  private HelloService helloService;

  @GetMapping
  String hello() {
    helloService.sayHello();
    return "HEllo";
  }

  @GetMapping("/hello2")
  String hello2() {
    helloService.sayHelloWrite();
    return "Hello2";
  }
}
