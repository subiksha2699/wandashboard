package com.example.sdwan.model.entity;

public class Organisation {

    private String id;
    private String name;
    private Integer totalSites;
    private String createdAt;
    private String updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalSites() {
        return totalSites;
    }

    public void setTotalSites(Integer totalSites) {
        this.totalSites = totalSites;
    }

    public Organisation() {
    }
}
