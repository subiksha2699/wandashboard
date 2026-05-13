package com.example.sdwan.service.impl;

import com.example.sdwan.mock.MockDataStore;
import com.example.sdwan.model.dto.Site;
import com.example.sdwan.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteServiceImpl implements SiteService {

    @Override
    public List<Site> getSitesByOrganisationId(String organisationId) {
        return MockDataStore.SITES.stream()
                .filter(site -> site.getOrganisationId().equals(organisationId))
                .collect(Collectors.toList());
    }
}
