package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.SupportDao;
import com.example.firstspringapp.dto.FullSupportDto;
import com.example.firstspringapp.dto.SupportDto;
import com.example.firstspringapp.repository.CustomerRepository;
import com.example.firstspringapp.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public SupportDto postNewOrder(Long id, SupportDao supportDao) {
        var customer = customerRepository.getById(id);

        Optional.of(customer).orElseThrow(RuntimeException::new);

        customer.createOrder(supportDao);

        return SupportDto.builder()
                .orderTitle(supportDao.getOrderTitle())
                .orderDescription(supportDao.getOrderDescription()).build();

    }

    @Override
    public List<FullSupportDto> getCustomerOrders(Long id) {
        var customer = Optional.ofNullable(customerRepository.getById(id)).orElseThrow();

        return supportRepository.getAllOrderFor(id).parallelStream()
                .map(dao -> new FullSupportDto(dao))
                .collect(Collectors.toList());
    }

    @Override
    public List<FullSupportDto> getAllOrders() {
        var listOfOrders = supportRepository.findAll();

        return listOfOrders.parallelStream()
                .map(dao -> new FullSupportDto(dao))
                .collect(Collectors.toList());
    }
}
