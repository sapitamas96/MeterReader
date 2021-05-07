package com.example.meterreading.controllers;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionInputDTO;
import com.example.meterreading.dtos.YearlyConsumptionOutputDTO;
import com.example.meterreading.services.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/consumptions")
public class MainController {

    private MeterReadingService meterReadingService;

    @Autowired
    public MainController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @GetMapping("/ip")
    public void ip(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
    }

    @PostMapping("/add")
    public ResponseEntity<MeterReadingDTO> saveMeterReading(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.save(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/yearly")
    public ResponseEntity<YearlyConsumptionOutputDTO> yearlyConsumption(@RequestBody YearlyConsumptionInputDTO yearlyConsumptionInputDTO, HttpServletRequest request) {
        return meterReadingService.yearlyConsumption(yearlyConsumptionInputDTO, request.getRemoteAddr());
    }
}
