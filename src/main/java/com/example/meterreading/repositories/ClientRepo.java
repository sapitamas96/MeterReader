package com.example.meterreading.repositories;

import com.example.meterreading.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Long> {

    Client findByIpAddress(String ipAddress);
}
