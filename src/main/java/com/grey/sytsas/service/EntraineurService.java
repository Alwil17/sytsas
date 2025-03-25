package com.grey.sytsas.service;

import com.grey.sytsas.payload.request.EntraineurRequest;
import com.grey.sytsas.payload.response.EntraineurResponse;

import java.util.List;

public interface EntraineurService {
    EntraineurResponse createEntraineur(EntraineurRequest request);
    EntraineurResponse updateEntraineur(String id, EntraineurRequest request);
    void deleteEntraineur(String id);
    EntraineurResponse getEntraineurById(String id);
    List<EntraineurResponse> getAllEntraineurs();
}

