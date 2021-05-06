package com.example.meterreading.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MeterReading {

    @Id
    @GeneratedValue
    private Long id;
    private String clientId;
    private Integer year;
    private String month;
    private Integer reading;
}
