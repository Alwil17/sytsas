package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraireRequest {
    @Min(0)
    private double debut;
    @Min(0)
    private double fin;
}
