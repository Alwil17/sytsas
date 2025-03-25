package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "carrieres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carriere {
    @Id
    private String id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @DBRef
    private Entraineur entraineur;
}
