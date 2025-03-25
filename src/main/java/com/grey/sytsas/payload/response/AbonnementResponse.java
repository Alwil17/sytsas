package com.grey.sytsas.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementResponse {
    private String id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean actif;

    private AdherentResponse adherent;
}
