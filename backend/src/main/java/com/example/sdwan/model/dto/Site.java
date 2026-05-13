package com.example.sdwan.model.dto;

public class Site {

    private String id;
    private String name;
    private String organisationId;
    private Integer totalEdges;
    private String createdAt;
    private String updatedAt;

    public Site() {}

    public Site(String id, String name, String organisationId, Integer totalEdges, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.organisationId = organisationId;
        this.totalEdges = totalEdges;
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

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
