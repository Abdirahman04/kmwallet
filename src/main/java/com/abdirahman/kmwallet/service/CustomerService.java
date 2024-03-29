package com.abdirahman.kmwallet.service;

import com.abdirahman.kmwallet.model.entity.Customer;
import com.abdirahman.kmwallet.repository.CustomerRepository;
import com.abdirahman.kmwallet.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerService() {
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new RuntimeException("No customer with id { "+ id +" } was found");
        else return customer.get();
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isEmpty()) throw new RuntimeException("No customer found for email {"+email+"}");
        else return customer.get();
    }

    public Customer addCustomer(Customer customer) {
        CustomerValidation.validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) throw new RuntimeException("No customer found for id {"+id+"}");
        else return customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
