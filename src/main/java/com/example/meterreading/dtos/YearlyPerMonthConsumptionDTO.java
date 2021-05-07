package com.example.meterreading.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YearlyPerMonthConsumptionDTO {

    private Integer year;
    private Map<String, Integer> monthlyConsumption;
}
