package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dao.SupportDao;
import com.example.firstspringapp.dto.FullSupportDto;
import com.example.firstspringapp.dto.SupportDto;
import com.example.firstspringapp.repository.CustomerRepository;
import com.example.firstspringapp.repository.SupportRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupportServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private SupportRepository supportRepository;

    @Mock
    private List<SupportDao> customerOrders;

    @InjectMocks
    private CustomerDao customerDao;

    @Autowired
    private SupportService supportService;

    @Test
    public void postNewOrderTest() {

        customerDao.setId(1L);
        customerDao.setFullName("test");

        Mockito.when(customerRepository.getById(1L)).thenReturn(customerDao);

        var supportDao = SupportDao.builder()
                .orderTitle("title")
                .orderDescription("desc").build();

        var result = supportService.postNewOrder(1L, supportDao);

        var daoToDto = SupportDto.builder()
                .orderTitle(supportDao.getOrderTitle())
                .orderDescription(supportDao.getOrderDescription()).build();

        Assert.assertEquals(result, daoToDto);

    }

    @Test
    public void getCustomerOrdersTest() {

        customerDao.setId(2L);
        customerDao.setFullName("test");

        Mockito.when(customerRepository.getById(2L)).thenReturn(customerDao);

        var list = List.of(SupportDao.builder()
                .orderTitle("title")
                .orderDescription("desc")
                .build());

        Mockito.when(supportRepository.getAllOrderFor(2L)).thenReturn(list);

        var listOfDto = list.stream()
                .map(FullSupportDto::new)
                .collect(Collectors.toList());

        var result = supportService.getCustomerOrders(2L);

        Assert.assertEquals(result.get(0).getOrderTitle(), listOfDto.get(0).getOrderTitle());
        Assert.assertEquals(result.get(0).getOrderDescription(), listOfDto.get(0).getOrderDescription());

    }

    @Test
    public void getAllOrders() {

        var listOfDao = List.of(SupportDao.builder()
                .orderTitle("title")
                .orderDescription("desc").build());

        Mockito.when(supportRepository.findAll()).thenReturn(listOfDao);

        var listOfFullDto = listOfDao.stream()
                .map(FullSupportDto::new)
                .collect(Collectors.toList());

        var result = supportService.getAllOrders();

        Assert.assertEquals(result.get(0).getOrderTitle(), listOfFullDto.get(0).getOrderTitle());
        Assert.assertEquals(result.get(0).getOrderDescription(), listOfFullDto.get(0).getOrderDescription());
    }
}