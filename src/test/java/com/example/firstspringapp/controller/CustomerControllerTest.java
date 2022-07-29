package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserTest() throws Exception {

        var customer = CustomerDao.builder().id(1L).fullName("Nick").build();
        var customerDto = CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void deleteUserByIdTest() throws Exception {

        var deletableCustomer = CustomerDto.builder()
                .id(1L)
                .fullName("Delete").build();

        Mockito.when(customerService.deleteUser(1L)).thenReturn(deletableCustomer);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is("Delete")));
    }

    @Test
    public void getUserTest() throws Exception {

        var showableCustomer = CustomerDto.builder()
                .id(1L)
                .fullName("Profile").build();

        Mockito.when(customerService.getUser(1L)).thenReturn(showableCustomer);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/profile").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is("Profile")));

    }

    @Test
    public void getAllUsersTest() throws Exception {

        var customerDto = CustomerDto.builder().id(1L).fullName("dto").build();
        var testableList = List.of(customerDto);

        Mockito.when(customerService.getAllUsers()).thenReturn(testableList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/statistics"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].fullName", Matchers.is("dto")));

    }

    @Test
    public void updateUserTest() throws Exception {

        var customer = CustomerDto.builder().id(1L).fullName("Updated").build();

        Mockito.when(customerService.updateUser(1L, "Updated")).thenReturn(customer);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/update").param("id", "1")
                .contentType(MediaType.TEXT_PLAIN).content("Updated"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is("Updated")));

    }
}