package com.grey.sytsas.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisponibiliteResponse {
    private String id;
    private LocalDate dateDispo;

    private List<HoraireResponse> horaires;
}
