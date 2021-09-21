package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    Customer customer = new Customer();


    @BeforeEach
    void setUp() {
        customer.setName("kadir");
        customer.setEmail("kadir@test.com");
        customer.setLastname("tuna");
    }

    @Test
    @Order(1)
    void createCustomerTest() {
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        Customer response = customerService.createCustomer(customer);
        Assertions.assertEquals(customer, response, "Hata");
    }

    @Test
    @Order(2)
    void createCustomerFailTest(){
        customer.setName(null);

        Customer response = customerService.createCustomer(this.customer);
        Assertions.assertNull(response);
    }
}