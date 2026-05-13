package com.example.sdwan.service;

import com.example.sdwan.model.dto.EdgeDevice;

import java.util.List;

public interface EdgeDeviceService {
    List<EdgeDevice> getEdgeDevicesBySiteId(String siteId);
}
