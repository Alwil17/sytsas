package com.grey.sytsas.controller;

import com.grey.sytsas.payload.request.CarriereRequest;
import com.grey.sytsas.payload.response.CarriereResponse;
import com.grey.sytsas.service.CarriereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carrieres")
public class CarriereController {
    @Autowired
    private CarriereService carriereService;

    @PostMapping
    public ResponseEntity<CarriereResponse> createCarriere(@RequestBody CarriereRequest request) {
        CarriereResponse carriereResponse = carriereService.createCarriere(request);
        return ResponseEntity.ok(carriereResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarriereResponse> updateCarriere(@PathVariable String id, @RequestBody CarriereRequest request) {
        carriereService.updateCarriere(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarriere(@PathVariable String id) {
        carriereService.deleteCarriere(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarriereResponse> getCarriereById(@PathVariable String id) {
        CarriereResponse carriereResponse = carriereService.getCarriereById(id);
        return ResponseEntity.ok(carriereResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarriereResponse>> getAllCarrieres() {
        List<CarriereResponse> carrieres = carriereService.getAllCarrieres();
        return ResponseEntity.ok(carrieres);
    }
}
