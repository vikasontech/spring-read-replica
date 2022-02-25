package com.example.springreadreplica.service.impl;

import com.example.springreadreplica.entity.AddressInfoEntity;
import com.example.springreadreplica.repo.AddressInfoRepo;
import com.example.springreadreplica.service.ReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadWriteServiceImpl implements ReadWriteService {

  private final AddressInfoRepo addressInfoRepo;

  @Override
  public String readOperations() {
    final AddressInfoEntity one = addressInfoRepo.findById(1L);
    System.out.println(one);
    return one.toString();
  }

  @Override
  public String writeOperations() {
    final AddressInfoEntity one = addressInfoRepo.findById(1L);
    System.out.println(one);
    return one.toString();
  }

}
