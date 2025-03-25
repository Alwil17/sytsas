package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "entraineurs")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Entraineur extends Personne {
    private int numCoach;
    private String specialite;
    private LocalDateTime dateEmb;
    private double salBase;

    @DBRef
    private List<Disponibilite> disponibilites;
}

