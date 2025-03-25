package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Disponibilite;
import com.grey.sytsas.entity.Horaire;
import com.grey.sytsas.payload.request.DisponibiliteRequest;
import com.grey.sytsas.payload.request.HoraireRequest;
import com.grey.sytsas.payload.response.DisponibiliteResponse;
import com.grey.sytsas.repository.DisponibiliteRepository;
import com.grey.sytsas.service.DisponibiliteService;
import com.grey.sytsas.service.HoraireService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Data
@RequiredArgsConstructor
public class DisponibiliteServiceImpl implements DisponibiliteService {

    private final DisponibiliteRepository disponibiliteRepository;
    private final HoraireService horaireService;

    // Méthode de conversion de l'entité vers l'objet Response
    private DisponibiliteResponse mapToResponse(Disponibilite disponibilite) {
        DisponibiliteResponse response = new DisponibiliteResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(disponibilite, DisponibiliteResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Disponibilite mapToEntity(DisponibiliteRequest request) {
        Disponibilite disponibilite = new Disponibilite();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        disponibilite = mapper.convertValue(request, Disponibilite.class);

        return disponibilite;
    }

    @Override
    public Disponibilite createDisponibilite(DisponibiliteRequest request) {
        Disponibilite disponibilite = mapToEntity(request);

        if (request.getHoraires() != null && !request.getHoraires().isEmpty()) {
            List<Horaire> horaires = request.getHoraires().stream()
                    .map(horaireService::createHoraire)
                    .collect(Collectors.toList());
            disponibilite.setHoraires(horaires);
        }

        disponibilite = disponibiliteRepository.save(disponibilite);
        return disponibilite;
    }

    @Override
    public Disponibilite updateDisponibilite(String id, DisponibiliteRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Disponibilite disponibilite = mapToEntity(request);
        disponibilite.setId(id);
        disponibilite = disponibiliteRepository.save(disponibilite);

        if (request.getHoraires() != null && !request.getHoraires().isEmpty()) {
            horaireService.deleteByDisponibiliteId(id);
            List<Horaire> horaires = request.getHoraires().stream()
                    .map(horaireService::createHoraire)
                    .collect(Collectors.toList());
            disponibilite.setHoraires(horaires);
        }
        return disponibilite;
    }

    @Override
    public void deleteDisponibilite(String id) {
        horaireService.deleteByDisponibiliteId(id);
        disponibiliteRepository.deleteById(id);
    }

    @Override
    public DisponibiliteResponse getDisponibiliteById(String id) {
        Disponibilite disponibilite = disponibiliteRepository.findById(id).orElse(null);
        return (disponibilite != null) ? mapToResponse(disponibilite) : null;
    }

    @Override
    public List<DisponibiliteResponse> getAllDisponibilites() {
        List<Disponibilite> salles = disponibiliteRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByEntraineurId(String id) {
        disponibiliteRepository.deleteAllByEntraineurId(id);
    }
}
