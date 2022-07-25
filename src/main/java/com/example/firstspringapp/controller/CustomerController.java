package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("Adding new customer")
    @PostMapping("/add")
    public CustomerDto createUser(@RequestBody CustomerDao customerDao) {
        return customerService.createUser(customerDao);
    }

    @ApiOperation("Deleting customer by id")
    @DeleteMapping("/delete/{id}")
    public CustomerDto deleteUserById(@PathVariable Long id) {
        return customerService.deleteUser(id);
    }

    @ApiOperation("Get customer profile")
    @GetMapping("/profile")
    public CustomerDto getUser(@RequestParam Long id) {
        return customerService.getUser(id);
    }

    @ApiOperation("Get all customers")
    @GetMapping("/statistics")
    public List<CustomerDto> getAllUsers() {
        return customerService.getAllUsers();
    }

    @ApiOperation("Update customer by id and new name")
    @PutMapping("/update")
    CustomerDto updateUser(@RequestParam Long id, @RequestBody String newName) {
        return customerService.updateUser(id, newName);
    }

}
