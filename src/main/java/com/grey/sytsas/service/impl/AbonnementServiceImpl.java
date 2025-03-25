package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Abonnement;
import com.grey.sytsas.entity.Adherent;
import com.grey.sytsas.payload.request.AbonnementRequest;
import com.grey.sytsas.payload.response.AbonnementResponse;
import com.grey.sytsas.repository.AbonnementRepository;
import com.grey.sytsas.repository.AdherentRepository;
import com.grey.sytsas.service.AbonnementService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Data
@RequiredArgsConstructor
public class AbonnementServiceImpl implements AbonnementService {

    private final AbonnementRepository abonnementRepository;
    private final AdherentRepository adherentRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private AbonnementResponse mapToResponse(Abonnement abonnement) {
        AbonnementResponse response = new AbonnementResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(abonnement, AbonnementResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Abonnement mapToEntity(AbonnementRequest request) {
        Abonnement abonnement = new Abonnement();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        abonnement = mapper.convertValue(request, Abonnement.class);

        return abonnement;
    }

    @Override
    public AbonnementResponse createAbonnement(AbonnementRequest request) {
        Abonnement abonnement = mapToEntity(request);

        Adherent adherent = adherentRepository.findById(request.getAdherentId()).orElseThrow();
        abonnement.setAdherent(adherent);

        Abonnement saved = abonnementRepository.save(abonnement);
        return mapToResponse(saved);
    }

    @Override
    public AbonnementResponse updateAbonnement(String id, AbonnementRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Abonnement abonnement = mapToEntity(request);
        abonnement.setId(id);
        Abonnement updated = abonnementRepository.save(abonnement);
        return mapToResponse(updated);
    }

    @Override
    public void deleteAbonnement(String id) {
        abonnementRepository.deleteById(id);
    }

    @Override
    public AbonnementResponse getAbonnementById(String id) {
        Abonnement abonnement = abonnementRepository.findById(id).orElse(null);
        return (abonnement != null) ? mapToResponse(abonnement) : null;
    }

    @Override
    public List<AbonnementResponse> getAllAbonnements() {
        List<Abonnement> salles = abonnementRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
