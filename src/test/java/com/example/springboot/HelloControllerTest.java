package com.example.springboot;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(HelloController.class)
@Disabled
class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        Model model = Mockito.mock(Model.class);
        assertEquals("index", controller.home(model));
    }
}
