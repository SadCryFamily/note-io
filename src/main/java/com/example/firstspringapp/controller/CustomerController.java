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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CustomerDto createUser(@RequestBody CustomerDao customerDao) {
        return customerService.createUser(customerDao);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CustomerDto deleteUserById(@PathVariable Long id) {
        return customerService.deleteUser(id);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public CustomerDto getUser(@RequestParam Long id) {
        return customerService.getUser(id);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public List<CustomerDao> getAllUsers() {
        return customerService.getAllUsers();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    CustomerDto updateUser(@RequestParam Long id, @RequestBody String newName) {
        return customerService.updateUser(id, newName);
    }

}
