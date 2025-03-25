package com.grey.sytsas.repository;

import com.grey.sytsas.entity.SalleSport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalleSportRepository extends MongoRepository<SalleSport, String> {
}
