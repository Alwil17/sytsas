package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleSportRequest {
    @NotNull
    private int numeroSalle;
    @NotBlank
    private String adresseSalle;
    @Min(1)
    private int capacite;
}
