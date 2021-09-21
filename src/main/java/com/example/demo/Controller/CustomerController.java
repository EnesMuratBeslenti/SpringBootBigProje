package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        Customer customer = customerService.inquireCustomer(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer result = customerService.createCustomer(customer);
       if (result != null){

           return new ResponseEntity<>(result ,HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") Long customerId){
        Customer deleteCustomerId = customerService.inquireCustomer(customerId);
        if (deleteCustomerId !=null) {
            customerService.deleteCustomer(customerId);
            return new  ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Long customerId,
                               @RequestParam(required =false) String name,
                               @RequestParam(required =false) String lastname,
                               @RequestParam(required=false) String email){
         customerService.updateCustomer(customerId,name,lastname,email);

    }

}

