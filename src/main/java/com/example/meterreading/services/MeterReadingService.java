package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionInputDTO;
import com.example.meterreading.dtos.YearlyConsumptionOutputDTO;
import org.springframework.http.ResponseEntity;

public interface MeterReadingService {

    ResponseEntity<YearlyConsumptionOutputDTO> yearlyConsumption(YearlyConsumptionInputDTO yearlyConsumptionInputDTO, String remoteAddr);

    ResponseEntity<MeterReadingDTO> save(MeterReadingDTO yearlyConsumptionOutputDTO, String remoteAddr);
}
