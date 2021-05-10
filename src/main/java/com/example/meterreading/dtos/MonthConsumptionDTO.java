package com.example.meterreading.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthConsumptionDTO {

    private Integer year;
    private String month;
    private Double consumption;
}
