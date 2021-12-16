package com.example.demo.Service.concretes;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
<<<<<<< HEAD:src/main/java/com/example/demo/Service/concretes/CustomerService.java
import com.example.demo.Service.ICustomerService;
=======
import com.example.demo.UtilityService;
>>>>>>> dabe32c5f04979aad0504c0de663ba7a9c5e1871:src/main/java/com/example/demo/Service/CustomerService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

<<<<<<< HEAD:src/main/java/com/example/demo/Service/concretes/CustomerService.java
    public Customer addCustomer(Customer customer) {
        int[] numeric = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        if (!customer.getName().isEmpty() && !customer.getName().matches("[a-zA-Z ]*\\d+.*") &&
                !customer.getEmail().isEmpty() &&
                !customer.getLastname().isEmpty() && !customer.getLastname().matches("[a-zA-Z ]*\\d+.*")) {
=======
    public Customer createCustomer(Customer customer) {
        int[]  numeric = {1,2,3,4,5,6,7,8,9,0};
        if   (          UtilityService.isNotEmpty(customer.getName())&& !customer.getName().matches("[a-zA-Z ]*\\d+.*") &&
                        UtilityService.isNotEmpty(customer.getEmail())&&
                        UtilityService.isNotEmpty(customer.getLastname())&& !customer.getLastname().matches("[a-zA-Z ]*\\d+.*")){
>>>>>>> dabe32c5f04979aad0504c0de663ba7a9c5e1871:src/main/java/com/example/demo/Service/CustomerService.java
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer inquireCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();

        } else {
            return null;
        }
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("" +
                    "Customer with id" + customerId + "does not exist");
        }
        customerRepository.deleteById(customerId);


    }

    @Transactional
    public void updateCustomer(Long customerId,
                               String name,
                               String lastname,
                               String email) {
        Customer customer = customerRepository.
                findById(customerId).
                orElseThrow(() -> new IllegalStateException("Customer with id" + customerId + "does not exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }
        if (lastname != null &&
                lastname.length() > 0 &&
                !Objects.equals(customer.getLastname(), lastname)) {
            customer.setLastname(lastname);

        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            customer.setEmail(email);
        }
    }


}

