package com.example.meterreading.controllers;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.services.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
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

    @GetMapping("/consumptions/yearly")
    public ResponseEntity<YearlyConsumptionDTO> yearlyConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.yearlyConsumption(meterReadingDTO, request.getRemoteAddr());
    }
}
