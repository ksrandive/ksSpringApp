package com.ks.app.controller;

import com.ks.app.model.AuthRequest;
import com.ks.app.model.Customer;
import com.ks.app.service.CustomerService;
//import com.ks.app.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cust")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/ping")
    public String ping(){
        return "Pong";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Customer>> getCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer customerId){
        return new ResponseEntity<>(customerService.getAllCustomerId(customerId), HttpStatus.OK);
    }

//    @GetMapping(value = "/by/{name}")
//    public ResponseEntity<UserDetails> getCustomerByName(@PathVariable(value = "name") String name){
//        return new ResponseEntity<>(customerService.loadUserByUsername(name), HttpStatus.OK);
//    }

    @PostMapping(value = "/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }
}
