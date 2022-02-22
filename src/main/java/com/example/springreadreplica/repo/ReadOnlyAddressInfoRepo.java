package com.example.springreadreplica.repo;

import com.example.springreadreplica.others.ReadOnlyRepository;
import com.example.springreadreplica.entity.AddressInfoEntity;
import org.springframework.data.repository.CrudRepository;

@ReadOnlyRepository
public interface ReadOnlyAddressInfoRepo extends CrudRepository<AddressInfoEntity, Long> {
  AddressInfoEntity findById(Long id);
}
