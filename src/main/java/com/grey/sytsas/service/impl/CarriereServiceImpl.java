package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.Carriere;
import com.grey.sytsas.payload.request.CarriereRequest;
import com.grey.sytsas.payload.response.CarriereResponse;
import com.grey.sytsas.repository.CarriereRepository;
import com.grey.sytsas.service.CarriereService;
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
public class CarriereServiceImpl implements CarriereService {

    private final CarriereRepository carriereRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private CarriereResponse mapToResponse(Carriere carriere) {
        CarriereResponse response = new CarriereResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(carriere, CarriereResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private Carriere mapToEntity(CarriereRequest request) {
        Carriere carriere = new Carriere();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        carriere = mapper.convertValue(request, Carriere.class);

        return carriere;
    }

    @Override
    public CarriereResponse createCarriere(CarriereRequest request) {
        Carriere carriere = mapToEntity(request);
        Carriere saved = carriereRepository.save(carriere);
        return mapToResponse(saved);
    }

    @Override
    public CarriereResponse updateCarriere(String id, CarriereRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        Carriere carriere = mapToEntity(request);
        carriere.setId(id);
        Carriere updated = carriereRepository.save(carriere);
        return mapToResponse(updated);
    }

    @Override
    public void deleteCarriere(String id) {
        carriereRepository.deleteById(id);
    }

    @Override
    public CarriereResponse getCarriereById(String id) {
        Carriere carriere = carriereRepository.findById(id).orElse(null);
        return (carriere != null) ? mapToResponse(carriere) : null;
    }

    @Override
    public List<CarriereResponse> getAllCarrieres() {
        List<Carriere> salles = carriereRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
