package com.grey.sytsas.controller;

import com.grey.sytsas.payload.request.EntraineurRequest;
import com.grey.sytsas.payload.response.EntraineurResponse;
import com.grey.sytsas.service.EntraineurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/entraineurs")
public class EntraineurController {
    @Autowired
    private EntraineurService entraineurService;

    @PostMapping
    public ResponseEntity<EntraineurResponse> createEntraineur(@RequestBody EntraineurRequest request) {
        EntraineurResponse entraineurResponse = entraineurService.createEntraineur(request);
        return ResponseEntity.ok(entraineurResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntraineurResponse> updateEntraineur(@PathVariable String id, @RequestBody EntraineurRequest request) {
        entraineurService.updateEntraineur(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntraineur(@PathVariable String id) {
        entraineurService.deleteEntraineur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntraineurResponse> getEntraineurById(@PathVariable String id) {
        EntraineurResponse entraineurResponse = entraineurService.getEntraineurById(id);
        return ResponseEntity.ok(entraineurResponse);
    }

    @GetMapping
    public ResponseEntity<List<EntraineurResponse>> getAllEntraineurs() {
        List<EntraineurResponse> entraineurs = entraineurService.getAllEntraineurs();
        return ResponseEntity.ok(entraineurs);
    }
}
