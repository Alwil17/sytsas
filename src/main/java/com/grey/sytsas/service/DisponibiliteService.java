package com.grey.sytsas.service;

import com.grey.sytsas.entity.Disponibilite;
import com.grey.sytsas.payload.request.DisponibiliteRequest;
import com.grey.sytsas.payload.response.DisponibiliteResponse;

import java.util.List;

public interface DisponibiliteService {
    Disponibilite createDisponibilite(DisponibiliteRequest request);
    Disponibilite updateDisponibilite(String id, DisponibiliteRequest request);
    void deleteDisponibilite(String id);
    DisponibiliteResponse getDisponibiliteById(String id);
    List<DisponibiliteResponse> getAllDisponibilites();

    void deleteAllByEntraineurId(String id);
}

