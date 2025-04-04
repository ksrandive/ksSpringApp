package com.ks.app.service;

import com.ks.app.model.Customer;
import com.ks.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getAllCustomerId(Integer customerId) {
        return customerRepository.getByCustomerId(customerId);
    }

    public Customer getAllCustomerName(String name) {
        return customerRepository.findByName(name).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
//        customer.setRoles("ROLE_USER");
//        customer.setPassword(encoder.encode(customer.getPassword()));
        customer.setUpdatedTs(LocalDateTime.now());
        customer.setUpdatedBy(customer.getRole());
        return customerRepository.save(customer);
    }
}
