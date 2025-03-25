package com.grey.sytsas.controller;

import com.grey.sytsas.payload.request.EquipementRequest;
import com.grey.sytsas.payload.response.EquipementResponse;
import com.grey.sytsas.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipements")
public class EquipementController {
    @Autowired
    private EquipementService equipementService;

    @PostMapping
    public ResponseEntity<EquipementResponse> createEquipement(@RequestBody EquipementRequest request) {
        EquipementResponse equipementResponse = equipementService.createEquipement(request);
        return ResponseEntity.ok(equipementResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementResponse> updateEquipement(@PathVariable String id, @RequestBody EquipementRequest request) {
        equipementService.updateEquipement(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable String id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementResponse> getEquipementById(@PathVariable String id) {
        EquipementResponse equipementResponse = equipementService.getEquipementById(id);
        return ResponseEntity.ok(equipementResponse);
    }

    @GetMapping
    public ResponseEntity<List<EquipementResponse>> getAllEquipements() {
        List<EquipementResponse> equipements = equipementService.getAllEquipements();
        return ResponseEntity.ok(equipements);
    }
}
