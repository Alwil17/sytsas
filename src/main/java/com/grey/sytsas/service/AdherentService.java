package com.grey.sytsas.service;

import com.grey.sytsas.payload.request.AdherentRequest;
import com.grey.sytsas.payload.response.AdherentResponse;

import java.util.List;

public interface AdherentService {
    AdherentResponse createAdherent(AdherentRequest request);
    AdherentResponse updateAdherent(String id, AdherentRequest request);
    void deleteAdherent(String id);
    AdherentResponse getAdherentById(String id);
    List<AdherentResponse> getAllAdherents();
}

