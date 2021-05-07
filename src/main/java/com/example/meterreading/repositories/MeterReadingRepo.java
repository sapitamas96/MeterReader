package com.example.meterreading.repositories;

import com.example.meterreading.models.MeterReading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MeterReadingRepo extends CrudRepository<MeterReading, Long> {

//    @Query(value = "SELECT Sum(reading) FROM MeterReading WHERE MeterReading.clientId = clientId AND MeterReading.year = year GROUP BY MeterReading.year")
    @Query(value = "SELECT Sum(reading) FROM meter_reading WHERE client_id = :clientId AND year = :year GROUP BY year", nativeQuery = true)
    Integer yearlyConsumption(String clientId, Integer year);
}
