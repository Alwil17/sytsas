package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Entraineur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntraineurRepository extends MongoRepository<Entraineur, String> {
}
