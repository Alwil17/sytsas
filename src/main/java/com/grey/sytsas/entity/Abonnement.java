package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "abonnements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {
    @Id
    private String id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean actif;

    @DBRef
    private Adherent adherent;
}
