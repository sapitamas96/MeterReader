package com.example.meterreading.repositories;

import com.example.meterreading.models.MeterReading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeterReadingRepo extends CrudRepository<MeterReading, Long> {

    @Query(value = "SELECT Sum(consumption) FROM meter_reading WHERE client_id = :clientId AND year = :year GROUP BY year", nativeQuery = true)
    Double yearlyConsumption(String clientId, Integer year);

    @Query(value = "SELECT meter_reading.month, consumption FROM meter_reading WHERE client_id = :clientId AND year = :year", nativeQuery = true)
    List<String[]> yearlyPerMonthConsumption(String clientId, Integer year);

    @Query(value = "SELECT consumption FROM meter_reading WHERE client_id = :clientId AND year = :year AND meter_reading.month = :month", nativeQuery = true)
    Double monthlyConsumption(String clientId, Integer year, String month);
}
