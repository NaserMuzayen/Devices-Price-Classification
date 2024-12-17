package com.example.DevicePriceClassificationApplication.service;

import com.example.DevicePriceClassificationApplication.model.Device;
import com.example.DevicePriceClassificationApplication.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;


    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

     // Get device by customId
     public Optional<Device> getDeviceByCustomId(Long customId) {
        return Optional.ofNullable(deviceRepository.findByCustomId(customId));
    }
    // Add a new device
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }
     // Update a device (if necessary)
     public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

}