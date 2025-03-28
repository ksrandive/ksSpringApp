//package com.ks.app.service;
//
//import com.ks.app.model.Customer;
//import com.ks.app.model.UserInfoDetails;
//import com.ks.app.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserInfoService implements UserDetailsService{
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
////    @Autowired
////    private PasswordEncoder encoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> userDetail = customerRepository.findByName(username); // Assuming 'email' is used as username
//
//        // Converting UserInfo to UserDetails
//        return userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//    }
//
//    public String addUser(Customer userInfo) {
//        // Encode password before saving the user
////        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
//        customerRepository.save(userInfo);
//        return "User Added Successfully";
//    }
//}
