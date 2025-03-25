package com.grey.sytsas.service;

import com.grey.sytsas.entity.Horaire;
import com.grey.sytsas.payload.request.HoraireRequest;
import com.grey.sytsas.payload.response.HoraireResponse;

import java.util.List;

public interface HoraireService {
    Horaire createHoraire(HoraireRequest request);
    Horaire updateHoraire(String id, HoraireRequest request);
    void deleteHoraire(String id);
    void deleteByDisponibiliteId(String disponibiliteId);
    HoraireResponse getHoraireById(String id);
    List<HoraireResponse> getAllHoraires();
}

