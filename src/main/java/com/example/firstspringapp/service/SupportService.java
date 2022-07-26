package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.SupportDao;
import com.example.firstspringapp.dto.FullSupportDto;
import com.example.firstspringapp.dto.SupportDto;

import java.util.List;

public interface SupportService {

    SupportDto postNewOrder(Long id, SupportDao supportDao);

    List<FullSupportDto> getCustomerOrders(Long id);

    List<FullSupportDto> getAllOrders();
}
