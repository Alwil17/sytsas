package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Entraineur;
import com.grey.sytsas.entity.Horaire;
import com.grey.sytsas.payload.request.HoraireRequest;
import com.grey.sytsas.payload.response.HoraireResponse;
import com.grey.sytsas.repository.EntraineurRepository;
import com.grey.sytsas.repository.HoraireRepository;
import com.grey.sytsas.service.HoraireService;
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
public class HoraireServiceImpl implements HoraireService {

    private final HoraireRepository horaireRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private HoraireResponse mapToResponse(Horaire horaire) {
        HoraireResponse response = new HoraireResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(horaire, HoraireResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Horaire mapToEntity(HoraireRequest request) {
        Horaire horaire = new Horaire();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        horaire = mapper.convertValue(request, Horaire.class);

        return horaire;
    }

    @Override
    public Horaire createHoraire(HoraireRequest request) {
        Horaire horaire = mapToEntity(request);
        horaire = horaireRepository.save(horaire);
        return horaire;
    }

    @Override
    public Horaire updateHoraire(String id, HoraireRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Horaire horaire = mapToEntity(request);
        horaire.setId(id);
        horaire = horaireRepository.save(horaire);
        return horaire;
    }

    @Override
    public void deleteHoraire(String id) {
        horaireRepository.deleteById(id);
    }

    @Override
    public void deleteByDisponibiliteId(String disponibiliteId) {
        horaireRepository.deleteAllByDisponibiliteId(disponibiliteId);
    }

    @Override
    public HoraireResponse getHoraireById(String id) {
        Horaire horaire = horaireRepository.findById(id).orElse(null);
        return (horaire != null) ? mapToResponse(horaire) : null;
    }

    @Override
    public List<HoraireResponse> getAllHoraires() {
        List<Horaire> salles = horaireRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
