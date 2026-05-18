package com.example.sdwan.mock;

import com.example.sdwan.model.enums.DeviceStatus;
import com.example.sdwan.model.dto.DevicePort;
import com.example.sdwan.model.dto.EdgeDevice;
import com.example.sdwan.model.dto.Organisation;
import com.example.sdwan.model.dto.Site;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class MockDataStore {

    public static final List<Organisation> ORGANISATIONS = List.of(
            new Organisation("org-1", "Acme Corp", 2, "2024-01-01", "2024-06-01"),
            new Organisation("org-2", "Globex Inc", 1, "2024-02-15", "2024-06-10")
    );

    public static final List<Site> SITES = List.of(
            new Site("site-1", "HQ", "org-1", 3, "2024-01-05", "2024-06-01"),
            new Site("site-2", "Branch East", "org-1", 2, "2024-01-10", "2024-06-01"),
            new Site("site-4", "Branch West", "org-1", 1, "2024-01-15", "2024-06-01"),
            new Site("site-5", "Branch North", "org-1", 2, "2024-01-20", "2024-06-01"),
            new Site("site-6", "Branch South", "org-1", 1, "2024-01-25", "2024-06-01"),
            new Site("site-7", "Remote Office", "org-1", 1, "2024-02-01", "2024-06-01"),
            new Site("site-3", "Main Office", "org-2", 1, "2024-02-20", "2024-06-10")
    );

    public static final List<EdgeDevice> EDGE_DEVICES = List.of(
            new EdgeDevice("dev-1", "Router-HQ-01", "site-1", "hub", "10.0.0.1", DeviceStatus.HEALTHY, at("2026-05-16T09:15:00Z"), null),
            new EdgeDevice("dev-2", "Router-HQ-02", "site-1", "spoke", "10.0.0.2", DeviceStatus.DEGRADED, at("2026-05-14T08:00:00Z"), at("2026-05-17T10:30:00Z")),
            new EdgeDevice("dev-3", "Router-HQ-03", "site-1", "spoke", "10.0.0.3", DeviceStatus.DOWN, at("2026-05-10T07:45:00Z"), at("2026-05-18T06:20:00Z")),
            new EdgeDevice("dev-4", "Router-East-01", "site-2", "hub", "10.1.0.1", DeviceStatus.HEALTHY, at("2026-05-15T11:05:00Z"), null),
            new EdgeDevice("dev-5", "Router-East-02", "site-2", "spoke", "10.1.0.2", DeviceStatus.MAINTENANCE, at("2026-05-13T13:40:00Z"), null),
            new EdgeDevice("dev-6", "Router-Main-01", "site-3", "hub", "10.2.0.1", DeviceStatus.HEALTHY, at("2026-05-12T10:10:00Z"), null)
    );

    // Port statuses for each edge device (WAN and LAN)
    public static final List<DevicePort> DEVICE_PORTS = List.of(
            // dev-1: Router-HQ-01 (healthy hub)
            new DevicePort("port-1", "dev-1", "WAN0", "WAN", "UP", "203.0.113.10", "255.255.255.252", "1 Gbps", 62.5, 8.2, 0.01),
            new DevicePort("port-2", "dev-1", "WAN1", "WAN", "UP", "198.51.100.2", "255.255.255.252", "1 Gbps", 34.1, 12.5, 0.03),
            new DevicePort("port-3", "dev-1", "LAN0", "LAN", "UP", "192.168.1.1", "255.255.255.0", "10 Gbps", 45.8, 0.5, 0.0),
            new DevicePort("port-4", "dev-1", "LAN1", "LAN", "UP", "192.168.2.1", "255.255.255.0", "10 Gbps", 28.3, 0.3, 0.0),
            new DevicePort("port-5", "dev-1", "LAN2", "LAN", "DOWN", "192.168.3.1", "255.255.255.0", "1 Gbps", 0.0, 0.0, 0.0),

            // dev-2: Router-HQ-02 (degraded spoke)
            new DevicePort("port-6", "dev-2", "WAN0", "WAN", "UP", "203.0.113.14", "255.255.255.252", "1 Gbps", 88.7, 45.3, 4.2),
            new DevicePort("port-7", "dev-2", "WAN1", "WAN", "DOWN", "198.51.100.6", "255.255.255.252", "1 Gbps", 0.0, 0.0, 0.0),
            new DevicePort("port-8", "dev-2", "LAN0", "LAN", "UP", "192.168.10.1", "255.255.255.0", "1 Gbps", 55.2, 1.1, 0.02),
            new DevicePort("port-9", "dev-2", "LAN1", "LAN", "UP", "192.168.11.1", "255.255.255.0", "1 Gbps", 12.4, 0.8, 0.0),

            // dev-3: Router-HQ-03 (down spoke)
            new DevicePort("port-10", "dev-3", "WAN0", "WAN", "DOWN", "203.0.113.18", "255.255.255.252", "1 Gbps", 0.0, 0.0, 0.0),
            new DevicePort("port-11", "dev-3", "LAN0", "LAN", "DOWN", "192.168.20.1", "255.255.255.0", "1 Gbps", 0.0, 0.0, 0.0),
            new DevicePort("port-12", "dev-3", "LAN1", "LAN", "DOWN", "192.168.21.1", "255.255.255.0", "1 Gbps", 0.0, 0.0, 0.0),

            // dev-4: Router-East-01 (healthy hub)
            new DevicePort("port-13", "dev-4", "WAN0", "WAN", "UP", "203.0.113.22", "255.255.255.252", "1 Gbps", 52.3, 15.7, 0.05),
            new DevicePort("port-14", "dev-4", "WAN1", "WAN", "UP", "198.51.100.10", "255.255.255.252", "500 Mbps", 41.8, 22.1, 0.08),
            new DevicePort("port-15", "dev-4", "LAN0", "LAN", "UP", "192.168.30.1", "255.255.255.0", "10 Gbps", 38.6, 0.4, 0.0),
            new DevicePort("port-16", "dev-4", "LAN1", "LAN", "UP", "192.168.31.1", "255.255.255.0", "1 Gbps", 22.1, 0.6, 0.0),

            // dev-5: Router-East-02 (maintenance spoke)
            new DevicePort("port-17", "dev-5", "WAN0", "WAN", "DOWN", "203.0.113.26", "255.255.255.252", "1 Gbps", 0.0, 0.0, 0.0),
            new DevicePort("port-18", "dev-5", "LAN0", "LAN", "DOWN", "192.168.40.1", "255.255.255.0", "1 Gbps", 0.0, 0.0, 0.0),

            // dev-6: Router-Main-01 (healthy hub)
            new DevicePort("port-19", "dev-6", "WAN0", "WAN", "UP", "203.0.113.30", "255.255.255.252", "1 Gbps", 70.2, 10.4, 0.02),
            new DevicePort("port-20", "dev-6", "LAN0", "LAN", "UP", "192.168.50.1", "255.255.255.0", "10 Gbps", 31.5, 0.2, 0.0),
            new DevicePort("port-21", "dev-6", "LAN1", "LAN", "UP", "192.168.51.1", "255.255.255.0", "1 Gbps", 18.9, 0.4, 0.0)
    );

    private static Date at(String isoInstant) {
        return Date.from(Instant.parse(isoInstant));
    }
}
