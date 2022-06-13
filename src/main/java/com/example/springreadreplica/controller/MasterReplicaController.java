package com.example.springreadreplica.controller;

import com.example.springreadreplica.service.MasterReplicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterReplicaController {
  @Autowired
  private MasterReplicaService service;

  @GetMapping("/replica")
  String read() {
    return service.operationOnReplica();
  }

  @GetMapping("/master")
  String write() {
    return service.operationOnMaster();
  }
}
