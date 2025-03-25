package com.grey.sytsas.controller;

import com.grey.sytsas.entity.Abonnement;
import com.grey.sytsas.payload.request.AbonnementRequest;
import com.grey.sytsas.payload.response.AbonnementResponse;
import com.grey.sytsas.service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/abonnements")
public class AbonnementController {
    @Autowired
    private AbonnementService abonnementService;

    @PostMapping
    public ResponseEntity<AbonnementResponse> createAbonnement(@RequestBody AbonnementRequest request) {
        AbonnementResponse abonnementResponse = abonnementService.createAbonnement(request);
        return ResponseEntity.ok(abonnementResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbonnementResponse> updateAbonnement(@PathVariable String id, @RequestBody AbonnementRequest request) {
        abonnementService.updateAbonnement(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable String id) {
        abonnementService.deleteAbonnement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbonnementResponse> getAbonnementById(@PathVariable String id) {
        AbonnementResponse abonnementResponse = abonnementService.getAbonnementById(id);
        return ResponseEntity.ok(abonnementResponse);
    }

    @GetMapping
    public ResponseEntity<List<AbonnementResponse>> getAllAbonnements() {
        List<AbonnementResponse> abonnements = abonnementService.getAllAbonnements();
        return ResponseEntity.ok(abonnements);
    }
}
