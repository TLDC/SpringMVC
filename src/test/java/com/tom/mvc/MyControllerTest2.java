package com.tom.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by thomasc on 14/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= MyConfig.class)
public class MyControllerTest2 {

    @Inject
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void fooTest() throws Exception {

//        MvcResult result = mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andReturn();

         MvcResult result  = mockMvc.perform(get("/test").accept(MediaType.TEXT_HTML))
                .andExpect(status().is2xxSuccessful())
                 .andExpect(view().name("view"))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(result.getResponse().getContentType());
    }



}
