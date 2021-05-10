package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.MonthConsumptionDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.dtos.YearlyPerMonthConsumptionDTO;
import org.springframework.http.ResponseEntity;

public interface MeterReadingService {

    ResponseEntity yearlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);

    ResponseEntity save(MeterReadingDTO yearlyConsumptionOutputDTO, String remoteAddr);

    ResponseEntity yearlyPerMonthConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);

    ResponseEntity monthlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);
}
