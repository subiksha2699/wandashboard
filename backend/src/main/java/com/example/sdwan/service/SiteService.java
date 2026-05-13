package com.example.sdwan.service;

import com.example.sdwan.model.dto.Site;

import java.util.List;

public interface SiteService {
    List<Site> getSitesByOrganisationId(String organisationId);
}
