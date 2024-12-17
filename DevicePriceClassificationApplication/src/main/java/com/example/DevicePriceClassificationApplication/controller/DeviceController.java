package com.example.DevicePriceClassificationApplication.controller;

import com.example.DevicePriceClassificationApplication.model.Device;
import com.example.DevicePriceClassificationApplication.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    private final String pythonApiUrl = "http://localhost:8000/predict";

    // Get all devices
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Get a specific device by customId
    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceByCustomId(id)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + id));
    }

    // Add a new device
    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceService.addDevice(device);
    }

    @PostMapping("/predict/{id}")
    public Device predictPrice(@PathVariable Long id) {
        // Retrieve the device by customId
        Device device = deviceService.getDeviceByCustomId(id)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + id));

        // Prepare the payload to send to the Python API
        Map<String, Object> payload = new HashMap<>();
        payload.put("battery_power", device.getBatteryPower());
        payload.put("blue", device.getBluetooth() );
        payload.put("clock_speed", device.getClockSpeed());
        payload.put("dual_sim", device.getDualSim() );
        payload.put("fc", device.getFrontCamera());
        payload.put("four_g", device.getFourG() );
        payload.put("int_memory", device.getInternalMemory());
        payload.put("m_dep", device.getMobileDepth());
        payload.put("mobile_wt", device.getMobileWeight());
        payload.put("n_cores", device.getNumCores());
        payload.put("pc", device.getPrimaryCamera());
        payload.put("px_height", device.getPixelHeight());
        payload.put("px_width", device.getPixelWidth());
        payload.put("ram", device.getRam());
        payload.put("sc_h", device.getScreenHeight());
        payload.put("sc_w", device.getScreenWidth());
        payload.put("talk_time", device.getTalkTime());
        payload.put("three_g", device.getThreeG() );
        payload.put("touch_screen", device.getTouchScreen() );
        payload.put("wifi", device.getWifi() );

        // Call the Python API
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send the request and get the response
            ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, payload, Map.class);

            // Update the device's priceRange with the prediction result
            Map responseBody = response.getBody();
            Integer predictedPriceRange = (Integer) responseBody.get("predicted_price");

            device.setPriceRange(predictedPriceRange);

            // Save the updated device
            return deviceService.updateDevice(device);

        } catch (Exception e) {
            throw new RuntimeException("Failed to call Python API: " + e.getMessage());
        }
    }

}
