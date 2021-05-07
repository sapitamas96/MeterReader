package com.example.meterreading.controllers;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.MonthConsumptionDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.dtos.YearlyPerMonthConsumptionDTO;
import com.example.meterreading.services.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consumptions")
public class MainController {

    private MeterReadingService meterReadingService;

    @Autowired
    public MainController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @PostMapping("/add")
    public ResponseEntity<MeterReadingDTO> saveMeterReading(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.save(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/yearly")
    public ResponseEntity<YearlyConsumptionDTO> yearlyConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.yearlyConsumption(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/yearly/month")
    public ResponseEntity<YearlyPerMonthConsumptionDTO> yearlyPerMonthConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.yearlyPerMonthConsumption(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/month")
    public ResponseEntity<MonthConsumptionDTO> monthConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.monthlyConsumption(meterReadingDTO, request.getRemoteAddr());
    }
}
