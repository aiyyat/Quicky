package com.company.poc.springreactive.repository;

import com.company.poc.springreactive.domain.Medicine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface InventoryRepository extends ReactiveMongoRepository<Medicine, String> {
}
