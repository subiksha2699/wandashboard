package com.example.sdwan.model.dto;

public class Organisation {

    private String id;
    private String name;
    private Integer totalSites;
    private String createdAt;
    private String updatedAt;

    public Organisation() {}

    public Organisation(String id, String name, Integer totalSites, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.totalSites = totalSites;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getTotalSites() { return totalSites; }
    public void setTotalSites(Integer totalSites) { this.totalSites = totalSites; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
