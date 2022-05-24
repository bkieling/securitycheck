package com.example.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureStubRunner(ids = {"com.example:service-maintenance:+:stubs:8081"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class RfaProviderImplTest {

    @Autowired
    private RfaProviderImpl rfaProviderImpl;

    //private Long rfaIdUnderTest;

    @Test
    void getRfaContentByIdTest() throws RfaNotFoundException {
        Long id = 1L;
        String rfaContent = "Test 1";

        String content = rfaProviderImpl.getRfaContentById(id);
        assertEquals(rfaContent, content);
    }

    @Test
    void getRfaContentByIdTest_NotFound() {
        Long id = 2L;
        Assertions.assertThrows(RfaNotFoundException.class, () -> rfaProviderImpl.getRfaContentById(id));
    }

    @Test
    void getRfaContentByIdTest_IdIsNull() {
        Long id = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> rfaProviderImpl.getRfaContentById(id));
    }

}
