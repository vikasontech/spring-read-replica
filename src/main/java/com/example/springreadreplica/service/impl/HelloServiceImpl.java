package com.example.springreadreplica.service.impl;

import com.example.springreadreplica.entity.AddressInfoEntity;
import com.example.springreadreplica.repo.AddressInfoRepo;
import com.example.springreadreplica.repo.ReadOnlyAddressInfoRepo;
import com.example.springreadreplica.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class HelloServiceImpl implements HelloService {
//  private final ReadOnlyAddressInfoRepo addressInfoRepo;
//  private final AddressInfoRepo addressInfoRepoWrite;
  private final AddressInfoRepo addressInfoRepo1;

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = true)
  public String sayHello() {
    final AddressInfoEntity one = addressInfoRepo1.findById(1L);
    System.out.println("Data from repository");
    System.out.println(one);
    return "helloRead";
  }

  @Override
  @org.springframework.transaction.annotation.Transactional
  public String sayHelloWrite() {
    final AddressInfoEntity one = addressInfoRepo1.findById(1L);
    System.out.println("Data from repository");
    System.out.println(one);
    return "hello Write";
  }

}
