package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Disponibilite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonneRepository extends MongoRepository<Disponibilite, String> {
}
