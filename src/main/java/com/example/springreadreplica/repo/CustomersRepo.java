package com.example.springreadreplica.repo;

import com.example.springreadreplica.entity.CustomersEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepo extends CrudRepository<CustomersEntity, Long> {
}
