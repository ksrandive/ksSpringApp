package com.ks.app.controller;

import com.ks.app.error.UserTokenException;
import com.ks.app.model.*;
import com.ks.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody ClientCustomer clientCustomer) {
        return ResponseEntity.ok(authService.addUser(clientCustomer));
    }

//    @PostMapping(value = "/token")
//    public String getToken(@RequestBody AuthRequest userCredential) {
//        Authentication authenticate = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(),
//                        userCredential.getPassword()));
//        if (authenticate.isAuthenticated()) {
//            return authService.generateToken(userCredential.getUsername());
//        } else {
//            throw new RuntimeException("User not valid");
//        }
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> loginApp(@RequestBody AuthRequest userCredential) {
        return ResponseEntity.ok(authService.authenticate(userCredential));
    }

    @GetMapping(value = "/validate")
    public String validateToken(@RequestParam("token") String token) {
        try{
            authService.validateToken(token);
        }catch (Exception e){
            throw new UserTokenException("Invalid token found!");
        }
        return "token is validated";
    }

    @GetMapping(value = "/token")
    public Token getToken(@RequestParam("header") String header){
        return authService.getToken(header).orElseGet(null);
    }
}
