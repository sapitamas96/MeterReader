package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
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
    public ResponseEntity<YearlyConsumptionDTO> yearlyConsumption(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        String clientId = meterReadingDTO.getClientId();
        int year = meterReadingDTO.getYear();

        if (validateClientAndSaveIfFirstFromIp(remoteAddr, clientId)) {
            return new ResponseEntity<>(new YearlyConsumptionDTO(year, meterReadingRepo.yearlyConsumption(clientId,year)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<MeterReadingDTO> save(MeterReadingDTO meterReadingDTO, String remoteAddr) {
        if (validateClientAndSaveIfFirstFromIp(remoteAddr, meterReadingDTO.getClientId()) && validateData(meterReadingDTO)) {
            meterReadingRepo.save(new MeterReading(meterReadingDTO));
            return ResponseEntity.ok().body(meterReadingDTO);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    private boolean validateData(MeterReadingDTO meterReadingDTO) {
        boolean validMonth = false;

        for (Month month : Month.values()) {
            if (month.toString().toLowerCase().equals(meterReadingDTO.getMonth().toLowerCase())) {
                validMonth = true;
            }
        }

        Integer reading = meterReadingDTO.getReading();

        return meterReadingDTO.getYear() <= Year.now().getValue() && validMonth && reading != null && reading >= 0;
    }
}
