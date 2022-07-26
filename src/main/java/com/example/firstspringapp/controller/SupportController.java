package com.example.firstspringapp.controller;

import com.example.firstspringapp.dto.FullSupportDto;
import com.example.firstspringapp.service.SupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class SupportController {

    @Autowired
    private SupportService supportService;

    @ApiOperation("Getting order by id")
    @GetMapping("/order")
    public List<FullSupportDto> getCustomerOrders(@RequestParam Long id) {
        return supportService.getCustomerOrders(id);
    }

    @ApiOperation("Getting all orders")
    @GetMapping("/orders")
    public List<FullSupportDto> getAllOrders() {
        return supportService.getAllOrders();
    }

}
