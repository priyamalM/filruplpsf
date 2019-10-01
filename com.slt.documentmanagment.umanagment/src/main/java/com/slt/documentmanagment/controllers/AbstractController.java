package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.config.ResourceServerPropConfig;
import org.springframework.web.client.RestTemplate;

public class AbstractController {

    public RestTemplate restService;
    public ResourceServerPropConfig resourceServerPropConfig;
    public final String BASERURI;

    public AbstractController(RestTemplate restService, ResourceServerPropConfig resourceServerPropConfig) {
        this.restService = restService;
        this.resourceServerPropConfig = resourceServerPropConfig;
        this.BASERURI=resourceServerPropConfig.getResourceServerUrl();
    }
}