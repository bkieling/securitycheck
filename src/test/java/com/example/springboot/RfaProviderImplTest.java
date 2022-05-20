package com.example.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RfaProviderImplTest {

    @Autowired
    private RfaProviderImpl rfaProviderImpl;

    @Test
    void getRfaContentByIdTest() throws RfaNotFoundException {
        Long id = 1L;
        String rfaContent = "Hello World";

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
