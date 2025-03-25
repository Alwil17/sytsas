package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Abonnement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbonnementRepository extends MongoRepository<Abonnement, String> {
}
