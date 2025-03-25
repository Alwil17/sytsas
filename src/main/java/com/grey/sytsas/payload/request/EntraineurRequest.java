package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EntraineurRequest extends PersonneRequest{
    @NotNull
    private int numCoach;
    @NotBlank
    private String specialite;
    @NotNull
    private LocalDateTime dateEmb;
    @Min(0)
    private double salBase;

    @NotBlank
    private String salleSportId;

    private List<DisponibiliteRequest> disponibilites;
}
