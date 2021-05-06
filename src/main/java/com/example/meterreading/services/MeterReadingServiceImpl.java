package com.example.meterreading.services;

import com.example.meterreading.dtos.MeterReadingDTO;
import com.example.meterreading.dtos.YearlyConsumptionDTO;
import com.example.meterreading.models.Client;
import com.example.meterreading.repositories.ClientRepo;
import com.example.meterreading.repositories.MeterReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    private boolean validateClientAndSaveIfFirstFromIp(String ip, String clientId) {
        Client client= clientRepo.findByIpAddress(ip);

        if (client == null) {
            clientRepo.save(new Client(ip, clientId));
            return true;
        }
        return clientId.equals(client.getClientId());
    }
}
