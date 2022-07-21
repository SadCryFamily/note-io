package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.CustomerDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {

        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(10L);
        customerDao.setFullName("Testable");

        String inputJson = new ObjectMapper().writeValueAsString(customerDao);

        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(10)))
                .andExpect(jsonPath("$.fullName", Matchers.is("Testable")))
                .andReturn();
    }

    @Test
    public void deleteUser() throws Exception {

        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(28L);
        customerDao.setFullName(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/28")).andReturn();
    }

    @Test
    public void getUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/profile?id=30")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(30)))
                .andExpect(jsonPath("$.fullName", Matchers.is("Testable")))
                .andReturn();
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }
}