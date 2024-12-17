package com.example.DevicePriceClassificationApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.DevicePriceClassificationApplication.model.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    Device findByCustomId(Long customId);
}
