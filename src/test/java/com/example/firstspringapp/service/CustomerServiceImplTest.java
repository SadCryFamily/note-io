package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dto.CustomerDto;
import com.example.firstspringapp.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void createUser() {
        var customer = CustomerDao.builder().id(1L).fullName("Test").build();
        var customerDto = CustomerDto.builder().id(1L).fullName("Test").build();

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        var result = customerService.createUser(customer);

        Assert.assertEquals(result.getFullName(), customerDto.getFullName());
        Assert.assertEquals(result.getId(), customerDto.getId());
    }

    @Test
    public void deleteUser() {
        var deletableCustomer = CustomerDao.builder().id(2L).fullName("Deletable").build();

        customerRepository.deleteById(2L);

        var result = customerService.deleteUser(2L);

        Assert.assertEquals(result.getId(), deletableCustomer.getId());
    }

    @Test
    public void getUser() {
        var getableCustomer = CustomerDao.builder().id(3L).fullName("Gettable").build();

        Mockito.when(customerRepository.findById(3L)).thenReturn(Optional.ofNullable(getableCustomer));

        var result = customerService.getUser(3L);

        Assert.assertEquals(result.getId(), getableCustomer.getId());
        Assert.assertEquals(result.getFullName(), getableCustomer.getFullName());
    }

    @Test
    public void updateUser() {
        var updatableCustomer = CustomerDao.builder().id(4L).fullName("Updatable").build();
        var updatedCustomerDto = CustomerDto.builder().id(4L).fullName("Set").build();

        Mockito.when(customerRepository.existsById(4L)).thenReturn(true);
        Mockito.when(customerRepository.getById(4L)).thenReturn(updatableCustomer);

        var result = customerService.updateUser(4L, "Set");

        Assert.assertEquals(result.getId(), updatedCustomerDto.getId());
        Assert.assertEquals(result.getFullName(), updatedCustomerDto.getFullName());
    }

    @Test
    public void getAllUsers() {
        List<CustomerDao> customersList = new ArrayList<>();

        var firstCustomer = CustomerDao.builder().id(5L).fullName("First").build();
        var secondCustomer = CustomerDao.builder().id(6L).fullName("Second").build();

        customersList.add(firstCustomer);
        customersList.add(secondCustomer);

        Mockito.when(customerRepository.save(firstCustomer)).thenReturn(firstCustomer);
        Mockito.when(customerRepository.save(secondCustomer)).thenReturn(secondCustomer);

        Mockito.when(customerRepository.findAll()).thenReturn(customersList);

        List<CustomerDto> result = customerService.getAllUsers();

        Assert.assertEquals(result.get(0), customersList.get(0));
        Assert.assertEquals(result.get(1), customersList.get(1));
    }
}