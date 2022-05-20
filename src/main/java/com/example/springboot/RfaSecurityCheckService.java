package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RfaSecurityCheckService {

    private Logger logger = LoggerFactory.getLogger(RfaSecurityCheckService.class);

    private RfaProvider rfaProvider;

    public RfaSecurityCheckService(RfaProvider rfaProvider) {
        this.rfaProvider = rfaProvider;
    }

    public void checkRfaContent(Long id) {
        isRfaContentSafe(id);
    }

    public boolean isRfaContentSafe(Long id) {
        String content = getRfaContentById(id);
        return !"virus".equals(content);
    }

    private String getRfaContentById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");
        try {
            return rfaProvider.getRfaContentById(id);
        } catch (RfaNotFoundException e) {

        }
        return "";
    }

}
