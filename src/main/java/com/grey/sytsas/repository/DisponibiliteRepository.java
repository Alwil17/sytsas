package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Disponibilite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DisponibiliteRepository extends MongoRepository<Disponibilite, String> {
    @Transactional
    @Query(value = "{ 'entraineur.$id' : ?0 }", delete = true)
    void deleteAllByEntraineurId(String entraineurId);
}
