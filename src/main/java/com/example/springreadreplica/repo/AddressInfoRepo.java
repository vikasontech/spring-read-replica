package com.example.springreadreplica.repo;

import com.example.springreadreplica.entity.AddressInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressInfoRepo extends CrudRepository<AddressInfoEntity, Long> {
  AddressInfoEntity findById(Long id);
}
