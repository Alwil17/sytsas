package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementRequest {
    @NotNull
    private LocalDateTime dateDebut;
    @NotNull
    private LocalDateTime dateFin;
    private boolean actif;
    @NotBlank
    private String adherentId;
}
