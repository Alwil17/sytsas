package com.grey.sytsas.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonneResponse {
    private String id;
    private int matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private String courriel;
}
