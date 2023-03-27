package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RfaProviderImpl implements RfaProvider {

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RfaProviderImpl.class);

    @Value("${service-maintenance.base-url}")
    private String serviceMaintenanceUrl;

    public RfaProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getRfaContentById(Long id) throws RfaNotFoundException {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");

        String url = serviceMaintenanceUrl + "/rfa/" + id;
        logger.debug("Trying to get RFA from Service Maintenance {}", url);
        try {
            RfaDto response = restTemplate.getForObject(serviceMaintenanceUrl + "/rfa/" + id, RfaDto.class);

            if (response != null) {
                return response.getContent();
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RfaNotFoundException("Rfa with ID '" + id + "' not found.");
        }

        return "";
    }

}
