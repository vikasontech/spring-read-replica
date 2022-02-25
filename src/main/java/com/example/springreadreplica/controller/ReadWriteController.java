package com.example.springreadreplica.controller;

import com.example.springreadreplica.service.ReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadWriteController {
  @Autowired
  private ReadWriteService helloService;

  @GetMapping("/read")
  String read() {
    return helloService.readOperations();
  }

  @GetMapping("/write")
  String write() {
    return helloService.writeOperations();
  }
}
