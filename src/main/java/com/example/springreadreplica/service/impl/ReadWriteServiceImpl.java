package com.example.springreadreplica.service.impl;

import com.example.springreadreplica.entity.CustomersEntity;
import com.example.springreadreplica.repo.CustomersRepo;
import com.example.springreadreplica.service.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadWriteServiceImpl implements ReadWriteService {

  private final CustomersRepo customersRepo;

  @Override
  public String readOperations() {
    final CustomersEntity one = customersRepo.findById(1L).get();
    System.out.println(one);
    return one.toString();
  }

  @Override
  public String writeOperations() {
    final CustomersEntity one = customersRepo.findById(1L).get();
    System.out.println(one);
    return one.toString();
  }

}
