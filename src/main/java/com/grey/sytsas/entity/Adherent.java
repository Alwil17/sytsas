package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adherents")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Adherent extends Personne {
    private int numMembre;
    private double poids;
}
