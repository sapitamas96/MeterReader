package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.MonthConsumptionDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.dtos.YearlyPerMonthConsumptionDTO;
import com.example.meterreading.models.Client;
import com.example.meterreading.models.MeterReading;
import com.example.meterreading.repositories.ClientRepo;
import com.example.meterreading.repositories.MeterReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MeterReadingServiceImpl implements  MeterReadingService {

    private MeterReadingRepo meterReadingRepo;
    private ClientRepo clientRepo;

    @Autowired
    public MeterReadingServiceImpl(MeterReadingRepo meterReadingRepo, ClientRepo clientRepo) {
        this.meterReadingRepo = meterReadingRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public ResponseEntity save(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        if (validateClientAndSaveIfFirstFromIp(remoteAddr, meterReadingDTO.getClientId()) && validateMeterReadingData(meterReadingDTO)) {
            meterReadingRepo.save(new MeterReading(meterReadingDTO));
            return ResponseEntity.ok().body(meterReadingDTO);
        }
        return ResponseEntity.badRequest().body(new HashMap<String, String>() {{put("error", "Invalid values or monthly reading already exists!");}});
    }

    @Override
    public ResponseEntity yearlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        String clientId = meterReadingDTO.getClientId();
        int year = meterReadingDTO.getYear();
        Double yearlyConsumption = meterReadingRepo.yearlyConsumption(clientId,year);

        if (validateClientAndSaveIfFirstFromIp(remoteAddr, clientId) && yearlyConsumption != null) {
            return new ResponseEntity<>(new YearlyConsumptionDTO(year, yearlyConsumption), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new HashMap<String, String>() {{put("error", "Invalid values!");}});
    }

    @Override
    public ResponseEntity yearlyPerMonthConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        String clientId = meterReadingDTO.getClientId();
        int year = meterReadingDTO.getYear();
        List<String[]> monthlyConsumption = meterReadingRepo.yearlyPerMonthConsumption(clientId, meterReadingDTO.getYear());

        if (validateClientAndSaveIfFirstFromIp(remoteAddr, clientId) && !monthlyConsumption.isEmpty()) {
            Map<String, Double> monthlyConsumptionMap = monthlyConsumption.stream().collect(Collectors.toMap(a -> a[0], a -> Double.parseDouble(a[1])));
            return new ResponseEntity<>(new YearlyPerMonthConsumptionDTO(year, monthlyConsumptionMap), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new HashMap<String, String>() {{put("error", "Invalid values!");}});
    }

    @Override
    public ResponseEntity monthlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        String clientId = meterReadingDTO.getClientId();
        int year = meterReadingDTO.getYear();
        String month = meterReadingDTO.getMonth();
        Double monthlyConsumption = meterReadingRepo.monthlyConsumption(clientId, year, month);

        if (validateClientAndSaveIfFirstFromIp(remoteAddr, clientId) && monthlyConsumption != null) {
            return new ResponseEntity<>(new MonthConsumptionDTO(year, month, monthlyConsumption), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new HashMap<String, String>() {{put("error", "Invalid values!");}});
    }

    private boolean validateClientAndSaveIfFirstFromIp(String ip, String clientId) {
        Client client= clientRepo.findByIpAddress(ip);

        if (client == null) {
            if (clientRepo.findAllByClientId(clientId) == null) {
                clientRepo.save(new Client(ip, clientId));
                return true;
            }
            return false;
        }
        return clientId.equals(client.getClientId());
    }

    private boolean validateMeterReadingData(MeterReadingDTO meterReadingDTO) {
        Double reading = meterReadingDTO.getConsumption();

        return validateDate(meterReadingDTO) && reading != null && reading >= 0 && !isMonthlyReadingAlreadyExist(meterReadingDTO);
    }

    private boolean validateDate(MeterReadingDTO meterReadingDTO) {
        boolean validMonth = false;

        for (Month month : Month.values()) {
            if (month.toString().equalsIgnoreCase(meterReadingDTO.getMonth())) {
                validMonth = true;
            }
        }

        return meterReadingDTO.getYear() <= Year.now().getValue() && validMonth;
    }

    private boolean isMonthlyReadingAlreadyExist(MeterReadingDTO meterReadingDTO) {
        return meterReadingRepo.monthlyConsumption(meterReadingDTO.getClientId(), meterReadingDTO.getYear(), meterReadingDTO.getMonth()) != null;
    }
}