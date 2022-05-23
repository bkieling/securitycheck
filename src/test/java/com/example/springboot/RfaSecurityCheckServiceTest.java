package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RfaSecurityCheckServiceTest {

    @Autowired
    private RfaSecurityCheckService rfaSecurityCheckService;
    @MockBean
    private RfaProvider rfaProvider;

    @Test
    void shouldReturnRfaNullContentSafe() throws RfaNotFoundException {
        Long id = 1L;
        assertTrue(rfaSecurityCheckService.isRfaContentSafe(id));
    }

    @Test
    void shouldReturnRfaContentSafe() throws RfaNotFoundException {
        Long id = 1L;
        String content = "Content is clean";
        when(rfaProvider.getRfaContentById(id)).thenReturn(content);
        assertTrue(rfaSecurityCheckService.isRfaContentSafe(id));
    }

    @Test
    void shouldReturnRfaContentIsUnsafe() throws RfaNotFoundException {
        Long id = 1L;
        String content = "virus";
        when(rfaProvider.getRfaContentById(id)).thenReturn(content);
        assertFalse(rfaSecurityCheckService.isRfaContentSafe(id));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRfaNotFound() throws RfaNotFoundException {
        Long id = 2L;
        when(rfaProvider.getRfaContentById(id)).thenThrow(new RfaNotFoundException("Rfa not found"));
        assertThrows(RfaNotFoundException.class, () -> rfaSecurityCheckService.isRfaContentSafe(id));
    }

}
