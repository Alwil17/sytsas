package com.grey.sytsas.repository;

import com.grey.sytsas.entity.Horaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HoraireRepository extends MongoRepository<Horaire, String> {

    @Transactional
    @Query(value = "{ 'disponibilite.$id' : ?0 }", delete = true)
    void deleteAllByDisponibiliteId(String disponibiliteId);
}
