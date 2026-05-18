package com.example.sdwan.api;

import com.example.sdwan.model.dto.EdgeDevice;
import com.example.sdwan.model.dto.Organisation;
import com.example.sdwan.model.dto.Site;
import com.example.sdwan.model.dto.SiteSummaryDTO;
import com.example.sdwan.service.EdgeDeviceService;
import com.example.sdwan.service.SiteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.example.sdwan.mock.MockDataStore;
import com.example.sdwan.exception.BadRequestException;
import com.example.sdwan.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class SdwanController {

    private final SiteService siteService;
    private final EdgeDeviceService edgeDeviceService;

    @Value("${mock.organisation.id}")
    private String mockOrgId;

    @Value("${mock.organisation.name}")
    private String mockOrgName;

    public SdwanController(SiteService siteService, EdgeDeviceService edgeDeviceService) {
        this.siteService = siteService;
        this.edgeDeviceService = edgeDeviceService;
    }

    @GetMapping("/health")
    public Map<String, String> getHealth() {
        return Map.of(
                "status", "ok",
                "service", "sdwan-assignment-starter-api",
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<Organisation>> getOrganizations() {
        return ResponseEntity.ok(MockDataStore.ORGANISATIONS);
    }

    @GetMapping("/sites")
    public ResponseEntity<List<SiteSummaryDTO>> getSites(@RequestParam String organisationId) {
        if (organisationId == null || organisationId.trim().isEmpty()) {
            throw new BadRequestException("Organisation ID is required");
        }
        boolean orgExists = MockDataStore.ORGANISATIONS.stream()
                .anyMatch(org -> org.getId().equals(organisationId));
        if (!orgExists) {
            throw new ResourceNotFoundException("Organisation ID not found: " + organisationId);
        }
        try {
            return ResponseEntity.ok(siteService.getSitesByOrganisationId(organisationId));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch sites", e);
        }
    }

    @GetMapping("/edge-devices")
    public ResponseEntity<List<EdgeDevice>> getEdgeDevices(@RequestParam String siteId) {
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new BadRequestException("Site ID is required");
        }
        return ResponseEntity.ok(edgeDeviceService.getEdgeDevicesBySiteId(siteId));
    }
}
