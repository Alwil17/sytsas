package com.grey.sytsas.controller;

import com.grey.sytsas.entity.Horaire;
import com.grey.sytsas.payload.request.HoraireRequest;
import com.grey.sytsas.payload.response.HoraireResponse;
import com.grey.sytsas.service.HoraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/horaires")
public class HoraireController {
    @Autowired
    private HoraireService horaireService;

    @PostMapping
    public ResponseEntity<Horaire> createHoraire(@RequestBody HoraireRequest request) {
        Horaire horaireResponse = horaireService.createHoraire(request);
        return ResponseEntity.ok(horaireResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoraireResponse> updateHoraire(@PathVariable String id, @RequestBody HoraireRequest request) {
        horaireService.updateHoraire(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoraire(@PathVariable String id) {
        horaireService.deleteHoraire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoraireResponse> getHoraireById(@PathVariable String id) {
        HoraireResponse horaireResponse = horaireService.getHoraireById(id);
        return ResponseEntity.ok(horaireResponse);
    }

    @GetMapping
    public ResponseEntity<List<HoraireResponse>> getAllHoraires() {
        List<HoraireResponse> horaires = horaireService.getAllHoraires();
        return ResponseEntity.ok(horaires);
    }
}
