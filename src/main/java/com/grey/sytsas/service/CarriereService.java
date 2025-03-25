package com.grey.sytsas.service;

import com.grey.sytsas.payload.request.CarriereRequest;
import com.grey.sytsas.payload.response.CarriereResponse;

import java.util.List;

public interface CarriereService {
    CarriereResponse createCarriere(CarriereRequest request);
    CarriereResponse updateCarriere(String id, CarriereRequest request);
    void deleteCarriere(String id);
    CarriereResponse getCarriereById(String id);
    List<CarriereResponse> getAllCarrieres();
}

