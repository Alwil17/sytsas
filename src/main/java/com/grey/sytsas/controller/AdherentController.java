package com.grey.sytsas.controller;

import com.grey.sytsas.payload.request.AdherentRequest;
import com.grey.sytsas.payload.response.AdherentResponse;
import com.grey.sytsas.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adherents")
public class AdherentController {
    @Autowired
    private AdherentService adherentService;

    @PostMapping
    public ResponseEntity<AdherentResponse> createAdherent(@RequestBody AdherentRequest request) {
        AdherentResponse adherentResponse = adherentService.createAdherent(request);
        return ResponseEntity.ok(adherentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdherentResponse> updateAdherent(@PathVariable String id, @RequestBody AdherentRequest request) {
        adherentService.updateAdherent(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable String id) {
        adherentService.deleteAdherent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdherentResponse> getAdherentById(@PathVariable String id) {
        AdherentResponse adherentResponse = adherentService.getAdherentById(id);
        return ResponseEntity.ok(adherentResponse);
    }

    @GetMapping
    public ResponseEntity<List<AdherentResponse>> getAllAdherents() {
        List<AdherentResponse> adherents = adherentService.getAllAdherents();
        return ResponseEntity.ok(adherents);
    }
}
