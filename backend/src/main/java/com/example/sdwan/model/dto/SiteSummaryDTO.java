package com.example.sdwan.model.dto;

/**
 * DTO for the sites API that includes device health counts
 * derived from the site's edge device list.
 */
public class SiteSummaryDTO {

    private String id;
    private String name;
    private String organisationId;
    private Integer totalEdges;
    private Integer healthyDevices;
    private Integer degradedDevices;
    private Integer downDevices;
    private String healthStatus; // HEALTHY, DEGRADED, DOWN
    private String createdAt;
    private String updatedAt;

    public SiteSummaryDTO() {}

    public SiteSummaryDTO(String id, String name, String organisationId, Integer totalEdges,
                          Integer healthyDevices, Integer degradedDevices, Integer downDevices,
                          String healthStatus, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.organisationId = organisationId;
        this.totalEdges = totalEdges;
        this.healthyDevices = healthyDevices;
        this.degradedDevices = degradedDevices;
        this.downDevices = downDevices;
        this.healthStatus = healthStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrganisationId() { return organisationId; }
    public void setOrganisationId(String organisationId) { this.organisationId = organisationId; }

    public Integer getTotalEdges() { return totalEdges; }
    public void setTotalEdges(Integer totalEdges) { this.totalEdges = totalEdges; }

    public Integer getHealthyDevices() { return healthyDevices; }
    public void setHealthyDevices(Integer healthyDevices) { this.healthyDevices = healthyDevices; }

    public Integer getDegradedDevices() { return degradedDevices; }
    public void setDegradedDevices(Integer degradedDevices) { this.degradedDevices = degradedDevices; }

    public Integer getDownDevices() { return downDevices; }
    public void setDownDevices(Integer downDevices) { this.downDevices = downDevices; }

    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
