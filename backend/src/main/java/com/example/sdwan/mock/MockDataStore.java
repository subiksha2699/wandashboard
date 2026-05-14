package com.example.sdwan.mock;

import com.example.sdwan.model.enums.DeviceStatus;
import com.example.sdwan.model.dto.EdgeDevice;
import com.example.sdwan.model.dto.Organisation;
import com.example.sdwan.model.dto.Site;
import org.springframework.stereotype.Component;

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
            new EdgeDevice("dev-1", "Router-HQ-01", "site-1", "hub", "10.0.0.1", DeviceStatus.HEALTHY, new Date(), null),
            new EdgeDevice("dev-2", "Router-HQ-02", "site-1", "spoke", "10.0.0.2", DeviceStatus.DEGRADED, new Date(), new Date()),
            new EdgeDevice("dev-3", "Router-HQ-03", "site-1", "spoke", "10.0.0.3", DeviceStatus.DOWN, null, new Date()),
            new EdgeDevice("dev-4", "Router-East-01", "site-2", "hub", "10.1.0.1", DeviceStatus.HEALTHY, new Date(), null),
            new EdgeDevice("dev-5", "Router-East-02", "site-2", "spoke", "10.1.0.2", DeviceStatus.MAINTENANCE, new Date(), null),
            new EdgeDevice("dev-6", "Router-Main-01", "site-3", "hub", "10.2.0.1", DeviceStatus.HEALTHY, new Date(), null)
    );
}
