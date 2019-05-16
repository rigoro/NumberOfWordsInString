package com.string.wordsCount.controller;

import com.string.wordsCount.form.ContentForm;
import com.string.wordsCount.service.StringOperationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StringOperationsController.class)
public class StringOperationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StringOperationsService service;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void indexPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void resultPageTest() throws Exception {
        MultiValueMap<String, String> formParams = toFormParams(new ContentForm("Some content"));

        mockMvc.perform(MockMvcRequestBuilders.post("/wordsCount")
                .params(formParams))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("content"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("result"))
                .andExpect(MockMvcResultMatchers.view().name("result"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private MultiValueMap<String, String> toFormParams(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(Map.class);
        Map<String, String> map = reader.readValue(objectMapper.writeValueAsString(o));

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        map.entrySet().stream()
                .forEach(e -> multiValueMap.add(e.getKey(), (e.getValue() == null ? "" : e.getValue())));
        return multiValueMap;
    }
}
