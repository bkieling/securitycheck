package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void index() throws Exception {
//        assertEquals("Greetings from Spring Boot + Tanzu + WeB fleet Security check change test2!", controller.index());
//
//        mockMvc
//            .perform(get("/"))
//            .andExpect(status().isOk())
//            .andExpect(content().string("Greetings from Spring Boot + Tanzu + WeB fleet Security check change test2!"));
//    }
}
