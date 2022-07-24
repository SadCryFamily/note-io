package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public CustomerDto createUser(@RequestBody CustomerDao customerDao) {
        return customerService.createUser(customerDao);
    }

    @DeleteMapping("/delete/{id}")
    public CustomerDto deleteUserById(@PathVariable Long id) {
        return customerService.deleteUser(id);
    }

    @GetMapping("/profile")
    public CustomerDto getUser(@RequestParam Long id) {
        return customerService.getUser(id);
    }

    @GetMapping("/statistics")
    public List<CustomerDao> getAllUsers() {
        return customerService.getAllUsers();
    }

    @PutMapping("/update")
    CustomerDto updateUser(@RequestParam Long id, @RequestBody String newName) {
        return customerService.updateUser(id, newName);
    }

}
