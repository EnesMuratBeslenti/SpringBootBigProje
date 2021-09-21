package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Customer createCustomer(Customer customer) {
        int[]  numeric = {1,2,3,4,5,6,7,8,9,0};
        if   (          UtilityService.isNotEmpty(customer.getName())&& !customer.getName().matches("[a-zA-Z ]*\\d+.*") &&
                        UtilityService.isNotEmpty(customer.getEmail())&&
                        UtilityService.isNotEmpty(customer.getLastname())&& !customer.getLastname().matches("[a-zA-Z ]*\\d+.*")){
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer inquireCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            return customer.get();

        }else{
            return null;
        }
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists){
            throw  new IllegalStateException("" +
                    "Customer with id" + customerId + "does not exist");
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

