package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Equipement;
import com.grey.sytsas.entity.SalleSport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipementRepository extends MongoRepository<Equipement, String> {
}
