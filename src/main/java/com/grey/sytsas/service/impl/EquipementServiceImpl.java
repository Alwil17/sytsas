package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Equipement;
import com.grey.sytsas.payload.request.EquipementRequest;
import com.grey.sytsas.payload.response.EquipementResponse;
import com.grey.sytsas.repository.EquipementRepository;
import com.grey.sytsas.service.EquipementService;
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
public class EquipementServiceImpl implements EquipementService {

    private final EquipementRepository equipementRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private EquipementResponse mapToResponse(Equipement equipement) {
        EquipementResponse response = new EquipementResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(equipement, EquipementResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Equipement mapToEntity(EquipementRequest request) {
        Equipement equipement = new Equipement();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        equipement = mapper.convertValue(request, Equipement.class);

        return equipement;
    }

    @Override
    public EquipementResponse createEquipement(EquipementRequest request) {
        Equipement equipement = mapToEntity(request);
        Equipement saved = equipementRepository.save(equipement);
        return mapToResponse(saved);
    }

    @Override
    public EquipementResponse updateEquipement(String id, EquipementRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Equipement equipement = mapToEntity(request);
        equipement.setId(id);
        Equipement updated = equipementRepository.save(equipement);
        return mapToResponse(updated);
    }

    @Override
    public void deleteEquipement(String id) {
        equipementRepository.deleteById(id);
    }

    @Override
    public EquipementResponse getEquipementById(String id) {
        Equipement equipement = equipementRepository.findById(id).orElse(null);
        return (equipement != null) ? mapToResponse(equipement) : null;
    }

    @Override
    public List<EquipementResponse> getAllEquipements() {
        List<Equipement> salles = equipementRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
