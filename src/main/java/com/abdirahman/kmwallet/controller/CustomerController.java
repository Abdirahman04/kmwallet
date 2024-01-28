package com.abdirahman.kmwallet.controller;

import com.abdirahman.kmwallet.model.entity.Customer;
import com.abdirahman.kmwallet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kmwallet")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerController() {
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers =  customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("customer/email/{email}")
    public ResponseEntity<Customer> getByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.addCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Customer customer1 = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customer1, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
