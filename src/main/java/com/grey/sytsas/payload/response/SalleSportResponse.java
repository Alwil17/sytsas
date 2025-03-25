package com.grey.sytsas.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleSportResponse {
    private String id;
    private int numeroSalle;
    private String adresseSalle;
    private int capacite;

    private List<EquipementResponse> equipements;
    private List<AbonnementResponse> abonnements;
    private List<CarriereResponse> carrieres;
}
