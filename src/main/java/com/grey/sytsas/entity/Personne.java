package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personnes")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Personne {
    @Id
    private String id;
    private int matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private String courriel;
}
