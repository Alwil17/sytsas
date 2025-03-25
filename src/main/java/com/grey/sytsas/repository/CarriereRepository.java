package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Carriere;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarriereRepository extends MongoRepository<Carriere, String> {
}
