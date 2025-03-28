package com.ks.app.service;

import com.ks.app.model.CustomUserDetails;
import com.ks.app.model.UserCredential;
import com.ks.app.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userDetails = userCredentialRepository.findByName(username);
        return userDetails.map(CustomUserDetails::new).orElseThrow(() ->new UsernameNotFoundException("user not found"+ username));
    }
}
