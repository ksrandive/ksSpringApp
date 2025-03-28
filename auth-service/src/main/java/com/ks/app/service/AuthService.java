package com.ks.app.service;

import com.ks.app.feign.CustomerClient;
import com.ks.app.model.ClientCustomer;
import com.ks.app.model.Customer;
import com.ks.app.model.UserCredential;
import com.ks.app.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomerClient customerClient;

    public String addUser(ClientCustomer clientCustomer){
        UserCredential save = userCredentialRepository.save(UserCredential.builder()
                .name(clientCustomer.getName())
                .password(passwordEncoder.encode(clientCustomer.getPassword()))
                .build());

        customerClient.addCustomer(Customer.builder()
                .customerId(save.getId())
                .name(clientCustomer.getName())
                .email(clientCustomer.getEmail())
                .address(clientCustomer.getAddress())
                .role("USER")
                .phoneNumber(clientCustomer.getPhoneNumber()).build());

        return "User added successfully";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }

}
