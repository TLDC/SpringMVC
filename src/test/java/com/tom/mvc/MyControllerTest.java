package com.tom.mvc;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by thomasc on 14/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= MyConfig.class)
public class MyControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = standaloneSetup(new MyController()).build();
    }

    @Test
    public void fooTest() throws Exception {

        MvcResult result = mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//         MvcResult result  = mockMvc.perform(get("/test").accept(MediaType.TEXT_HTML).contentType(MediaType.TEXT_HTML))
//                .andExpect(status().is2xxSuccessful())
//                 .andExpect(view().name("view"))
//               .andExpect(content().contentType(MediaType.TEXT_HTML))
//                .andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(result.getResponse().getContentType());
    }



}
