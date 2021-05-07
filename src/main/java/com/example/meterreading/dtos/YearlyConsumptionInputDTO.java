package com.example.meterreading.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YearlyConsumptionInputDTO {

    private String clientId;
    private Integer year;
}
