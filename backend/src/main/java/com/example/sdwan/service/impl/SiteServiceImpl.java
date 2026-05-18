package com.example.sdwan.service.impl;

import com.example.sdwan.mock.MockDataStore;
import com.example.sdwan.model.dto.EdgeDevice;
import com.example.sdwan.model.dto.Site;
import com.example.sdwan.model.dto.SiteSummaryDTO;
import com.example.sdwan.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sdwan.model.enums.DeviceStatus.*;

@Service
public class SiteServiceImpl implements SiteService {

    @Override
    public List<SiteSummaryDTO> getSitesByOrganisationId(String organisationId) {

        List<SiteSummaryDTO> summaries = MockDataStore.SITES.stream()
                .filter(site -> site.getOrganisationId().equals(organisationId))
                .toList()
                .stream().map(site -> {
            List<EdgeDevice> devices = MockDataStore.EDGE_DEVICES.stream()
                    .filter(device -> device.getSiteId().equals(site.getId()))
                    .toList();
            int healthy = (int) devices.stream().filter(d -> d.getStatus() == HEALTHY).count();
            int degraded = (int) devices.stream().filter(d -> d.getStatus() == DEGRADED).count();
            int down = (int) devices.stream().filter(d -> d.getStatus() == DOWN).count();
            String status;
            if (!devices.isEmpty() && healthy == devices.size()) {
                status = "HEALTHY";
            } else if (!devices.isEmpty() && down == devices.size()) {
                status = "DOWN";
            } else if (degraded > 0 || down > 0) {
                status = "DEGRADED";
            } else {
                status = "HEALTHY";
            }
            return new SiteSummaryDTO(
                    site.getId(),
                    site.getName(),
                    site.getOrganisationId(),
                    site.getTotalEdges(),
                    healthy,
                    degraded,
                    down,
                    status,
                    site.getCreatedAt(),
                    site.getUpdatedAt()
            );
        }).toList();

        return summaries;

    }
}
