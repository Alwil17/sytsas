package com.grey.sytsas.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementResponse {
    private String id;
    private int numEquip;
    private String nomEquip;
    private String fonctionEquip;
    private int quantite;
}
