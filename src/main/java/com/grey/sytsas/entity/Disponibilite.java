package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "disponibilites")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilite {
    @Id
    private String id;
    private LocalDate dateDispo;

    @DBRef
    private List<Horaire> horaires;
}
