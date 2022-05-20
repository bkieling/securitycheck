package com.example.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RfaSecurityCheckServiceTest {

    @Autowired
    private RfaSecurityCheckService rfaSecurityCheckService;

    @Test
    void getRfaContentByIdTest() throws RfaNotFoundException {
        Long id = 1L;
        String content = rfaSecurityCheckService.getRfaContentById(id);
        assertEquals("Hello world", content);
    }

    @Test
    void getRfaContentByIdTest_NotFound() {
        Long id = 2L;
        Assertions.assertThrows(RfaNotFoundException.class, () -> rfaSecurityCheckService.getRfaContentById(id));
    }

    @Test
    void getRfaContentByIdTest_IdIsNull() {
        Long id = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> rfaSecurityCheckService.getRfaContentById(id));
    }
}
