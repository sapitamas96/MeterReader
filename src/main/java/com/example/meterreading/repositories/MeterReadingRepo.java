package com.example.meterreading.repositories;

import com.example.meterreading.models.MeterReading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeterReadingRepo extends CrudRepository<MeterReading, Long> {

    @Query(value = "SELECT Sum(reading) FROM MeterReading WHERE MeterReading.clientId = clientId AND MeterReading.year = year GROUP BY MeterReading.year")
    Integer yearlyConsumption(@Param("clientId") String clientId, @Param("year") Integer year);
}
