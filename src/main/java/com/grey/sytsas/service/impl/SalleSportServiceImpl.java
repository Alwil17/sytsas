package com.grey.sytsas.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grey.sytsas.entity.SalleSport;
import com.grey.sytsas.payload.request.SalleSportRequest;
import com.grey.sytsas.payload.response.SalleSportResponse;
import com.grey.sytsas.repository.SalleSportRepository;
import com.grey.sytsas.service.SalleSportService;
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
public class SalleSportServiceImpl implements SalleSportService {

    private final SalleSportRepository salleSportRepository;

    // Méthode de conversion de l'entité vers l'objet Response
    private SalleSportResponse mapToResponse(SalleSport salleSport) {
        SalleSportResponse response = new SalleSportResponse();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        response = mapper.convertValue(salleSport, SalleSportResponse.class);

        return response;
    }

    // Méthode de conversion de l'objet Request vers l'entité
    private SalleSport mapToEntity(SalleSportRequest request) {
        SalleSport salleSport = new SalleSport();
        salleSport.setNumeroSalle(request.getNumeroSalle());
        salleSport.setAdresseSalle(request.getAdresseSalle());
        salleSport.setCapacite(request.getCapacite());
        return salleSport;
    }

    @Override
    public SalleSportResponse createSalleSport(SalleSportRequest request) {
        SalleSport salleSport = mapToEntity(request);
        SalleSport saved = salleSportRepository.save(salleSport);
        return mapToResponse(saved);
    }

    @Override
    public SalleSportResponse updateSalleSport(String id, SalleSportRequest request) {
        // On mappe le request vers l'entité et on définit l'id pour la mise à jour
        SalleSport salleSport = mapToEntity(request);
        salleSport.setId(id);
        SalleSport updated = salleSportRepository.save(salleSport);
        return mapToResponse(updated);
    }

    @Override
    public void deleteSalleSport(String id) {
        salleSportRepository.deleteById(id);
    }

    @Override
    public SalleSportResponse getSalleSportById(String id) {
        SalleSport salleSport = salleSportRepository.findById(id).orElse(null);
        return (salleSport != null) ? mapToResponse(salleSport) : null;
    }

    @Override
    public List<SalleSportResponse> getAllSalleSports() {
        List<SalleSport> salles = salleSportRepository.findAll();
        return salles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
