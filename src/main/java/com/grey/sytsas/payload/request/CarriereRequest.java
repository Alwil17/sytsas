package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarriereRequest {
    @NotNull
    private LocalDateTime dateDebut;
    @NotNull
    private LocalDateTime dateFin;
}
