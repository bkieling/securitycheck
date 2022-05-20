package com.example.springboot;

import org.springframework.stereotype.Service;

@Service
public class RfaSecurityCheckService {
    public String getRfaContentById(Long id) throws RfaNotFoundException {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");
        if (id == 1)
            return "Hello world";

        throw new RfaNotFoundException("Rfa " + id + " not found");
    }
}
