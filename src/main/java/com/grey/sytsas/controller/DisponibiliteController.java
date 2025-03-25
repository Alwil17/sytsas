package com.grey.sytsas.controller;

import com.grey.sytsas.entity.Disponibilite;
import com.grey.sytsas.payload.request.DisponibiliteRequest;
import com.grey.sytsas.payload.response.DisponibiliteResponse;
import com.grey.sytsas.service.DisponibiliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disponibilites")
public class DisponibiliteController {
    @Autowired
    private DisponibiliteService disponibiliteService;

    @PostMapping
    public ResponseEntity<Disponibilite> createDisponibilite(@RequestBody DisponibiliteRequest request) {
        Disponibilite disponibiliteResponse = disponibiliteService.createDisponibilite(request);
        return ResponseEntity.ok(disponibiliteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibiliteResponse> updateDisponibilite(@PathVariable String id, @RequestBody DisponibiliteRequest request) {
        disponibiliteService.updateDisponibilite(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable String id) {
        disponibiliteService.deleteDisponibilite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibiliteResponse> getDisponibiliteById(@PathVariable String id) {
        DisponibiliteResponse disponibiliteResponse = disponibiliteService.getDisponibiliteById(id);
        return ResponseEntity.ok(disponibiliteResponse);
    }

    @GetMapping
    public ResponseEntity<List<DisponibiliteResponse>> getAllDisponibilites() {
        List<DisponibiliteResponse> disponibilites = disponibiliteService.getAllDisponibilites();
        return ResponseEntity.ok(disponibilites);
    }
}
