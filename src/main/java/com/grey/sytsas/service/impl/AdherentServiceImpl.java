package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Adherent;
import com.grey.sytsas.payload.request.AdherentRequest;
import com.grey.sytsas.payload.response.AdherentResponse;
import com.grey.sytsas.repository.AdherentRepository;
import com.grey.sytsas.service.AdherentService;
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
public class AdherentServiceImpl implements AdherentService {

    private final AdherentRepository adherentRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private AdherentResponse mapToResponse(Adherent adherent) {
        AdherentResponse response = new AdherentResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(adherent, AdherentResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Adherent mapToEntity(AdherentRequest request) {
        Adherent adherent = new Adherent();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        adherent = mapper.convertValue(request, Adherent.class);

        return adherent;
    }

    @Override
    public AdherentResponse createAdherent(AdherentRequest request) {
        Adherent adherent = mapToEntity(request);
        Adherent saved = adherentRepository.save(adherent);
        return mapToResponse(saved);
    }

    @Override
    public AdherentResponse updateAdherent(String id, AdherentRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Adherent adherent = mapToEntity(request);
        adherent.setId(id);
        Adherent updated = adherentRepository.save(adherent);
        return mapToResponse(updated);
    }

    @Override
    public void deleteAdherent(String id) {
        adherentRepository.deleteById(id);
    }

    @Override
    public AdherentResponse getAdherentById(String id) {
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        return (adherent != null) ? mapToResponse(adherent) : null;
    }

    @Override
    public List<AdherentResponse> getAllAdherents() {
        List<Adherent> salles = adherentRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
