package com.example.springreadreplica.service.impl;

import com.example.springreadreplica.entity.CustomersEntity;
import com.example.springreadreplica.repo.CustomersRepo;
import com.example.springreadreplica.service.MasterReplicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterReplicaServiceImpl implements MasterReplicaService {

  private final CustomersRepo customersRepo;

  @Override
  public String operationOnReplica() {
    final CustomersEntity one = customersRepo.findById(1L).get();
    System.out.println(one);
    return one.toString();
  }

  @Override
  public String operationOnMaster() {
    final CustomersEntity one = customersRepo.findById(1L).get();
    System.out.println(one);
    return one.toString();
  }

}
