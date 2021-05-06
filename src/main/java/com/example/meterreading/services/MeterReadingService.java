package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import org.springframework.http.ResponseEntity;

public interface MeterReadingService {

    ResponseEntity<YearlyConsumptionDTO> yearlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr);
}
