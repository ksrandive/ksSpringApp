package com.ks.app.service;

import com.ks.app.feign.CustomerClient;
import com.ks.app.model.*;
import com.ks.app.repository.TokenRepository;
import com.ks.app.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    TokenRepository tokenRepository;

    public AuthenticationResponse addUser(ClientCustomer clientCustomer){

        if(userCredentialRepository.findByName(clientCustomer.getName()).isPresent()) {
            return new AuthenticationResponse(null, null,"User already exist");
        }

        UserCredential save = userCredentialRepository.save(UserCredential.builder()
                .name(clientCustomer.getName())
                .password(passwordEncoder.encode(clientCustomer.getPassword()))
                .isActive(1)
                .role(Role.valueOf(clientCustomer.getRole()))
                .updatedBy(clientCustomer.getRole())
                .updatedTs(LocalDateTime.now())
                .build());

        customerClient.addCustomer(Customer.builder()
                .customerId(save.getUserId())
                .name(clientCustomer.getName())
                .email(clientCustomer.getEmail())
                .address(clientCustomer.getAddress())
                .role(clientCustomer.getRole())
                .phoneNumber(clientCustomer.getPhoneNumber()).build());

        String accessToken = jwtService.generateToken(save.getName());
//        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, "", save);

        return new AuthenticationResponse(accessToken, "","User registration was successful");
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
        jwtService.isValid(token, userDetails);
    }

    public AuthenticationResponse authenticate(AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            if(authenticate.isAuthenticated()){
                UserCredential user = userCredentialRepository.findByName(request.getUsername()).orElseThrow();
                String accessToken = jwtService.generateToken(user.getName());
//        String refreshToken = jwtService.generateRefreshToken(user);

                revokeAllTokenByUser(user);
                saveUserToken(accessToken, "", user);

                return new AuthenticationResponse(accessToken, "", "User login was successful");
            }
        }catch (Exception exception){
            return new AuthenticationResponse("", "", exception.getMessage());
        }
        return null;
    }

    private void revokeAllTokenByUser(UserCredential user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getUserId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> t.setLoggedOut(true));

        tokenRepository.saveAll(validTokens);
    }

    public Optional<Token> getToken(String token){
        return tokenRepository.findByAccessToken(token);
    }

    private void saveUserToken(String accessToken, String refreshToken, UserCredential user) {

        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

}
