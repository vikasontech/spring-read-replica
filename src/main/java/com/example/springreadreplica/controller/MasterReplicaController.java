package com.example.springreadreplica.controller;

import com.example.springreadreplica.service.MasterReplicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterReplicaController {
  @Autowired
  private MasterReplicaService service;

  /**
   * This endpoint should return the data from MASTER DATABASE
   * @return
   */

  @GetMapping("/master")
  String operationOnMaster() {
    return service.operationOnMaster();
  }
  /**
   * This endpoint should return the data from REPLICA DATABASE
   * @return
   */
  @GetMapping("/replica")
  String operationOnReplica() {
    return service.operationOnReplica();
  }

}
