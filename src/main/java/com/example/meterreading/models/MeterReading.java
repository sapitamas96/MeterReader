package com.example.meterreading.models;

import com.example.meterreading.dtos.MeterReadingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private Integer year;
    private String month;
    private Double consumption;

    public MeterReading(MeterReadingDTO meterReadingDTO) {
        this.clientId = meterReadingDTO.getClientId();
        this.year = meterReadingDTO.getYear();
        this.month = meterReadingDTO.getMonth();
        this.consumption = meterReadingDTO.getConsumption();
    }
}
