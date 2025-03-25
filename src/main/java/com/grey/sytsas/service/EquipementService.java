package com.grey.sytsas.service;

import com.grey.sytsas.payload.request.EquipementRequest;
import com.grey.sytsas.payload.response.EquipementResponse;

import java.util.List;

public interface EquipementService {
    EquipementResponse createEquipement(EquipementRequest request);
    EquipementResponse updateEquipement(String id, EquipementRequest request);
    void deleteEquipement(String id);
    EquipementResponse getEquipementById(String id);
    List<EquipementResponse> getAllEquipements();
}

