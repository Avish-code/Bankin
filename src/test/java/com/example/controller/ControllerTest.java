package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;
import com.example.repo.BankRepo;
import com.example.controller.CustomerController;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private BankRepo bankRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CustomerController customerController;

    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();
        customers.add(new Customer(1L, "John", "john123", "password", 30, "123456789","john@example.com", "123 Main St", "9854761230", 1000.0));
        customers.add(new Customer(2L, "Jane", "jane456", "password", 25, "987654321","jane@example.com", "456 Elm St","9854761231" , 1500.0));
    }

    @Test
    void testRead() {
        when(bankRepo.findAll()).thenReturn(customers);
        
        List<Customer> result = customerController.read();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());
    }

    // Add other test methods for remaining controller methods
    // Make sure to test positive and negative scenarios

}
