package com.ks.app.controller;

import com.ks.app.model.AuthRequest;
import com.ks.app.model.ClientCustomer;
import com.ks.app.model.UserCredential;
import com.ks.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public String addUser(@RequestBody ClientCustomer clientCustomer) {
        return authService.addUser(clientCustomer);
    }

    @PostMapping(value = "/token")
    public String getToken(@RequestBody AuthRequest userCredential) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(),
                        userCredential.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(userCredential.getUsername());
        } else {
            throw new RuntimeException("User not valid");
        }
    }

    @GetMapping(value = "/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "token is validated";
    }
}
