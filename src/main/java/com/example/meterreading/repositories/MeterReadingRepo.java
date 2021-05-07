package com.example.meterreading.repositories;

import com.example.meterreading.models.MeterReading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;

public interface MeterReadingRepo extends CrudRepository<MeterReading, Long> {

    @Query(value = "SELECT Sum(reading) FROM meter_reading WHERE client_id = :clientId AND year = :year GROUP BY year", nativeQuery = true)
    Integer yearlyConsumption(String clientId, Integer year);

    @Query(value = "SELECT meter_reading.month, reading FROM meter_reading WHERE client_id = :clientId AND year = :year", nativeQuery = true)
    List<String[]> yearlyPerMonthConsumption(String clientId, Integer year);
}
