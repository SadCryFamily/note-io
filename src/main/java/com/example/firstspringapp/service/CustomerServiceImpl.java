package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createUser(@RequestBody CustomerDao customerDao) {
        customerRepository.save(customerDao);
        return CustomerDto.builder().id(customerDao.getId()).fullName(customerDao.getFullName()).build();
    }

    @Override
    public CustomerDto deleteUser(Long id) {
        customerRepository.deleteById(id);
        return CustomerDto.builder().id(id).build();
    }

    @Override
    public CustomerDto getUser(Long id) {
        Optional<CustomerDao> profile = customerRepository.findById(id);
        return CustomerDto.builder().id(id).fullName(profile.get().getFullName()).build();
    }

    @Override
    public CustomerDto updateUser(Long id, String newName) {
        if (customerRepository.existsById(id)) {
            CustomerDao currentCustomer = customerRepository.getById(id);
            currentCustomer.setFullName(newName);
            customerRepository.save(currentCustomer);

            return CustomerDto.builder().id(id).fullName(newName).build();
        }
        throw new RuntimeException();
    }

    @Override
    public List<CustomerDao> getAllUsers() {
        return new ArrayList<>(customerRepository.findAll());
    }
}
