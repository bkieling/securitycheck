package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RfaSecurityCheckService {

    private final Logger logger = LoggerFactory.getLogger(RfaSecurityCheckService.class);

    private final RfaProvider rfaProvider;

    public RfaSecurityCheckService(RfaProvider rfaProvider) {
        this.rfaProvider = rfaProvider;
    }

    public void checkRfaContent(Long id) {
        try {
            boolean contentSafe = isRfaContentSafe(id);

            if (contentSafe) {
                logger.info("RFA with id {} is secure", id);
            } else {
                logger.warn("RFA with id {} is NOT secure", id);
            }
            // true, then send RFA virus checked
            // false, then do nothing for now
        } catch (RfaNotFoundException e) {
            logger.warn("RFA with id {} not found. Could not check for virus.", id, e);
        }
    }

    public boolean isRfaContentSafe(Long id) throws RfaNotFoundException {
        String content = getRfaContentById(id);
        return !"virus".equals(content);
    }

    private String getRfaContentById(Long id) throws RfaNotFoundException {
        if (id == null)
            throw new IllegalArgumentException("RFA id invalid (must be not null)");
        return rfaProvider.getRfaContentById(id);
    }

}
