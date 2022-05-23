package com.example.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 8090)
public class RfaProviderImplTest {

    @Autowired
    private RfaProviderImpl rfaProviderImpl;

    //private Long rfaIdUnderTest;

    void setupWireMock(Long id) throws JsonProcessingException {
        RfaDto rfaDto = new RfaDto();
        rfaDto.setId(id);
        rfaDto.setContent("Hello World");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(rfaDto);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/rfa/" + id))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(content)));
    }

    @Test
    void getRfaContentByIdTest() throws RfaNotFoundException, JsonProcessingException {
        Long id = 1L;
        String rfaContent = "Hello World";
        setupWireMock(id);

        String content = rfaProviderImpl.getRfaContentById(id);
        assertEquals(rfaContent, content);
    }

    @Test
    void getRfaContentByIdTest_NotFound() throws JsonProcessingException {
        Long id = 2L;
        setupWireMock(1L);
        Assertions.assertThrows(RfaNotFoundException.class, () -> rfaProviderImpl.getRfaContentById(id));
    }

    @Test
    void getRfaContentByIdTest_IdIsNull() {
        Long id = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> rfaProviderImpl.getRfaContentById(id));
    }

}
