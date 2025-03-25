package com.grey.sytsas.service;

import com.grey.sytsas.entity.Abonnement;
import com.grey.sytsas.payload.request.AbonnementRequest;
import com.grey.sytsas.payload.response.AbonnementResponse;

import java.util.List;

public interface AbonnementService {
    AbonnementResponse createAbonnement(AbonnementRequest request);
    AbonnementResponse updateAbonnement(String id, AbonnementRequest request);
    void deleteAbonnement(String id);
    AbonnementResponse getAbonnementById(String id);
    List<AbonnementResponse> getAllAbonnements();
}

