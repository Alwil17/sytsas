package com.grey.sytsas.controller;

import com.grey.sytsas.payload.request.SalleSportRequest;
import com.grey.sytsas.payload.response.SalleSportResponse;
import com.grey.sytsas.service.SalleSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salles")
public class SalleSportController {
    @Autowired
    private SalleSportService salleService;

    @PostMapping
    public ResponseEntity<SalleSportResponse> createSalleSport(@RequestBody SalleSportRequest request) {
        SalleSportResponse salleResponse = salleService.createSalleSport(request);
        return ResponseEntity.ok(salleResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalleSportResponse> updateSalleSport(@PathVariable String id, @RequestBody SalleSportRequest request) {
        salleService.updateSalleSport(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalleSport(@PathVariable String id) {
        salleService.deleteSalleSport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleSportResponse> getSalleSportById(@PathVariable String id) {
        SalleSportResponse salleResponse = salleService.getSalleSportById(id);
        return ResponseEntity.ok(salleResponse);
    }

    @GetMapping
    public ResponseEntity<List<SalleSportResponse>> getAllSalleSports() {
        List<SalleSportResponse> salles = salleService.getAllSalleSports();
        return ResponseEntity.ok(salles);
    }
}
