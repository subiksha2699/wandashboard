package com.example.sdwan.service;

import com.example.sdwan.model.dto.Site;
import com.example.sdwan.model.dto.SiteSummaryDTO;

import java.util.List;

public interface SiteService {
    List<SiteSummaryDTO> getSitesByOrganisationId(String organisationId) throws Exception;
}
