package com.example.demo.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer () {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists){
            throw  new IllegalStateException("" +
                    "Student with id" + customerId + "does not exist");
        }
        customerRepository.deleteById(customerId);


    }
    @Transactional
    public void updateCustomer(Long customerId,
                               String name,
                               String lastname,
                               String email) {
        Customer customer= customerRepository.
                findById(customerId).
                orElseThrow(() -> new IllegalStateException("Customer with id" + customerId + "does not exist"));
        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(customer.getName(), name)){
            customer.setName(name);
        }
        if (lastname != null &&
                lastname.length() > 0 &&
                !Objects.equals(customer.getLastname(), lastname)){
                 customer.setLastname(lastname);

        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)){
            customer.setEmail(email);

        }
    }
}