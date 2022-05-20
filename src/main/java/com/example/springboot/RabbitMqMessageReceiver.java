package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqMessageReceiver {
    Logger logger = LoggerFactory.getLogger(RabbitMqMessageReceiver.class);

    private RfaSecurityCheckService rfaSecurityCheckService;

    public RabbitMqMessageReceiver(RfaSecurityCheckService rfaSecurityCheckService) {
        this.rfaSecurityCheckService = rfaSecurityCheckService;
    }

    @RabbitListener(queues = "rfa.uploaded")
    public void receive(RfaUploadedEvent rfaUploadedEvent) {
        Long id = rfaUploadedEvent.getId();
        logger.info("Received RFA uploaded. Id = '" + id + "'");
        rfaSecurityCheckService.getRfaContentById(id);
    }

}
