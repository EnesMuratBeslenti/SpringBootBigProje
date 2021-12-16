package com.example.demo.Service;

import com.example.demo.Model.Customer;

import java.util.List;

public interface ICustomerService {

     List<Customer> getCustomer();
     Customer addCustomer(Customer customer);
     Customer findCustomerById(Long customerId);
     void deleteCustomer(Long customerId);
     void updateCustomer(Long customerId,
                         String name,
                         String lastname,
                         String email);


}
