package com.grey.sytsas.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisponibiliteRequest {
    @NotNull
    private LocalDate dateDispo;

    private List<HoraireRequest> horaires;
}
