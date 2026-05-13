package com.example.sdwan.service.impl;

import com.example.sdwan.mock.MockDataStore;
import com.example.sdwan.model.dto.EdgeDevice;
import com.example.sdwan.service.EdgeDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EdgeDeviceServiceImpl implements EdgeDeviceService {

    @Override
    public List<EdgeDevice> getEdgeDevicesBySiteId(String siteId) {
        return MockDataStore.EDGE_DEVICES.stream()
                .filter(device -> device.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }
}
