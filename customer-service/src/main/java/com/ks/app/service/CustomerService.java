package com.ks.app.service;

import com.ks.app.model.Customer;
//import com.ks.app.model.UserInfoDetails;
import com.ks.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;

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
        return customerRepository.save(customer);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> userDetail = customerRepository.findByName(username); // Assuming 'email' is used as username
//        // Converting UserInfo to UserDetails
//        return userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//    }
}
