package com.grey.sytsas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "equipements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {
    @Id
    private String id;

    private int numEquip;
    private String nomEquip;
    private String fonctionEquip;
    private int quantite;
}
