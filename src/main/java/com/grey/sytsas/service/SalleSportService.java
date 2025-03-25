package com.grey.sytsas.service;

import com.grey.sytsas.payload.request.SalleSportRequest;
import com.grey.sytsas.payload.response.SalleSportResponse;

import java.util.List;

public interface SalleSportService {
    SalleSportResponse createSalleSport(SalleSportRequest request);
    SalleSportResponse updateSalleSport(String id, SalleSportRequest request);
    void deleteSalleSport(String id);
    SalleSportResponse getSalleSportById(String id);
    List<SalleSportResponse> getAllSalleSports();
}

