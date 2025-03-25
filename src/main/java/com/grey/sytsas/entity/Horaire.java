package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "horaires")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Horaire {
    @Id
    private String id;
    private double debut;
    private double fin;
}