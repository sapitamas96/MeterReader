package com.example.meterreading.controllers;

import com.example.meterreading.dtos.MeterReadingDTO;
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
    public ResponseEntity saveMeterReading(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.save(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/yearly")
    public ResponseEntity yearlyConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.yearlyConsumption(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/yearly/month")
    public ResponseEntity yearlyPerMonthConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.yearlyPerMonthConsumption(meterReadingDTO, request.getRemoteAddr());
    }

    @GetMapping("/month")
    public ResponseEntity monthConsumption(@RequestBody MeterReadingDTO meterReadingDTO, HttpServletRequest request) {
        return meterReadingService.monthlyConsumption(meterReadingDTO, request.getRemoteAddr());
    }
}
