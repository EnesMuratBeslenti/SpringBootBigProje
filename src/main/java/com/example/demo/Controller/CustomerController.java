package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path ="api/v1/customer")
public class CustomerController {

    final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/list")
    public List<Customer> getCustomer(){
            return  customerService.getCustomer();

    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<Customer> getFindById(@PathVariable("customerId") final Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        if (    !customer.getName().isEmpty()  &&
                !customer.getLastname().isEmpty() &&
                !customer.getEmail().isEmpty()  )
        {
              customerService.addCustomer(customer);
              return new ResponseEntity<>(customer , HttpStatus.CREATED);

        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Long customerId,
                               @RequestParam(required =false) String name,
                               @RequestParam(required =false) String lastname,
                               @RequestParam(required=false) String email){
        customerService.updateCustomer(customerId,name,lastname,email);

    }

}

