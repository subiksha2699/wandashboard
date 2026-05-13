package com.example.sdwan.model.entity;


public class Site {

    private String id;

    private String name;
    private Integer totalEdges;
    private Integer organisationId;
    private String createdAt;
    private String updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalEdges() {
        return totalEdges;
    }

    public void setTotalEdges(Integer totalEdges) {
        this.totalEdges = totalEdges;
    }
    public Integer getOrganisationId() {
        return organisationId;
    }


}
