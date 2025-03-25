package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Adherent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdherentRepository extends MongoRepository<Adherent, String> {
}
