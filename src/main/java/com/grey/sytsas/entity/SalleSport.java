package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "salles_sport")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalleSport {
    @Id
    private String id;
    private int numeroSalle;
    private String adresseSalle;
    private int capacite;

    @DBRef(lazy = false)
    private List<Equipement> equipements;

    @DBRef(lazy = false)
    private List<Abonnement> abonnements;

    @DBRef(lazy = false)
    private List<Carriere> carrieres;
}
