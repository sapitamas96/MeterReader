package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.dtos.YearlyPerMonthConsumptionDTO;
import org.springframework.http.ResponseEntity;

public interface MeterReadingService {

    ResponseEntity<YearlyConsumptionDTO> yearlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);

    ResponseEntity<MeterReadingDTO> save(MeterReadingDTO yearlyConsumptionOutputDTO, String remoteAddr);

    ResponseEntity<YearlyPerMonthConsumptionDTO> yearlyPerMonthConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);
}
