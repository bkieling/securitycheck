package com.example.springboot;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RfaProviderImpl implements RfaProvider {

    private final RestTemplate restTemplate;

    public RfaProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getRfaContentById(Long id) throws RfaNotFoundException {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");
        if (id == 1L) {
            RfaDto response = restTemplate.getForObject("http://localhost:8090/rfa/" + id, RfaDto.class);
            if (response != null) {
                return response.getContent();
            }
        }
        throw new RfaNotFoundException("Rfa with ID " + id + " not found.");
    }

}
