package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.dto.NoteDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createUser(CustomerDao customerDao);

    CustomerDto deleteUser(Long id);

    CustomerDto getUser(Long id);

    CustomerDto updateUser(Long id, String newName);

    List<CustomerDao> getAllUsers();

}
