package com.example.springboot;

import org.springframework.stereotype.Component;

@Component
public class RfaProviderImpl implements RfaProvider {

    @Override
    public String getRfaContentById(Long id) throws RfaNotFoundException {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");
        if (id == 1L) {
            return "Hello World";
        }
        throw new RfaNotFoundException("Rfa with ID " + id + " not found.");
    }

}
