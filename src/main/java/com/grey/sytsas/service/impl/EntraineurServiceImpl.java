package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Disponibilite;
import com.grey.sytsas.entity.Entraineur;
import com.grey.sytsas.entity.Horaire;
import com.grey.sytsas.payload.request.DisponibiliteRequest;
import com.grey.sytsas.payload.request.EntraineurRequest;
import com.grey.sytsas.payload.request.HoraireRequest;
import com.grey.sytsas.payload.response.EntraineurResponse;
import com.grey.sytsas.repository.EntraineurRepository;
import com.grey.sytsas.service.DisponibiliteService;
import com.grey.sytsas.service.EntraineurService;
import com.grey.sytsas.service.HoraireService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Data
@RequiredArgsConstructor
public class EntraineurServiceImpl implements EntraineurService {

    private final EntraineurRepository entraineurRepository;
    private final MongoTemplate mongoTemplate;
    private final DisponibiliteService disponibiliteService;
    private final HoraireService horaireService;

    // Méthode de conversion de l'entité vers l'objet Response
    private EntraineurResponse mapToResponse(Entraineur entraineur) {
        EntraineurResponse response = new EntraineurResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(entraineur, EntraineurResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Entraineur mapToEntity(EntraineurRequest request) {
        Entraineur entraineur = new Entraineur();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        entraineur = mapper.convertValue(request, Entraineur.class);

        return entraineur;
    }

    @Override
    public EntraineurResponse createEntraineur(EntraineurRequest request) {
        Entraineur entraineur = mapToEntity(request);

        if (request.getDisponibilites() != null && !request.getDisponibilites().isEmpty()) {
            List<Disponibilite> disponibilites = request.getDisponibilites().stream()
                    .map(disponibiliteService::createDisponibilite).collect(Collectors.toList());
            entraineur.setDisponibilites(disponibilites);
        }

        entraineur = entraineurRepository.save(entraineur);
        return mapToResponse(entraineur);
    }

    @Override
    public EntraineurResponse updateEntraineur(String id, EntraineurRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Entraineur entraineur = mapToEntity(request);
        entraineur.setId(id);

        if (request.getDisponibilites() != null && !request.getDisponibilites().isEmpty()) {
            disponibiliteService.deleteAllByEntraineurId(id);
            List<Disponibilite> disponibilites = request.getDisponibilites().stream()
                    .map(disponibiliteService::createDisponibilite).collect(Collectors.toList());
            entraineur.setDisponibilites(disponibilites);
        }
        entraineur = entraineurRepository.save(entraineur);
        return mapToResponse(entraineur);
    }

    @Override
    public void deleteEntraineur(String id) {
        disponibiliteService.deleteAllByEntraineurId(id);
        entraineurRepository.deleteById(id);
    }

    @Override
    public EntraineurResponse getEntraineurById(String id) {
        Entraineur entraineur = entraineurRepository.findById(id).orElse(null);
        return (entraineur != null) ? mapToResponse(entraineur) : null;
    }

    @Override
    public List<EntraineurResponse> getAllEntraineurs() {
        List<Entraineur> salles = entraineurRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Nouvelle méthode utilisant l'agrégation avec $lookup
    public List<EntraineurResponse> getAllEntraineursWithDetails() {
        // Premier lookup : joindre la collection "disponibilites" où le champ "entraineur" correspond à l'_id de l'entraîneur
        LookupOperation lookupDisponibilite = LookupOperation.newLookup()
                .from("disponibilites")
                .localField("_id")
                .foreignField("entraineur")
                .as("disponibilites");

        // Second lookup : joindre la collection "horaires" depuis le champ "horaire" dans les disponibilites
        LookupOperation lookupHoraire = LookupOperation.newLookup()
                .from("horaires")
                .localField("disponibilites.horaire")
                .foreignField("_id")
                .as("horaires");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupDisponibilite,
                lookupHoraire
        );

        AggregationResults<Entraineur> results = mongoTemplate.aggregate(
                aggregation,
                "entraineurs", // Nom de la collection d'entraîneurs
                Entraineur.class
        );

        List<Entraineur> entraineursWithDetails = results.getMappedResults();
        return entraineursWithDetails.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
