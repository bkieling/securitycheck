package com.example.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RfaSecurityCheckServiceTest {

    @Autowired
    private RfaSecurityCheckService rfaSecurityCheckService;
    @MockBean
    private RfaProvider rfaProvider;

    @Test
    void getRfaContentByIdTest() throws RfaNotFoundException {
        Long id = 1L;
        String rfaContent = "Hello World";
        Mockito.when(rfaProvider.getRfaContentByID(id)).thenReturn(rfaContent);
        String content = rfaSecurityCheckService.getRfaContentById(id);

        assertEquals(rfaContent, content);
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
