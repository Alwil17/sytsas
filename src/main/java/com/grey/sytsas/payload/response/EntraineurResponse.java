package com.grey.sytsas.payload.response;

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
public class EntraineurResponse extends PersonneResponse{
    private int numCoach;
    private String specialite;
    private LocalDateTime dateEmb;
    private double salBase;

    private List<DisponibiliteResponse> disponibilites;
}

