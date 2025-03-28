package com.ks.app.feign;

import com.ks.app.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="CUSTOMER-SERVICE", path = "/cust")
public interface CustomerClient {

    @PostMapping(value = "/add")
    ResponseEntity<Customer> addCustomer(@RequestBody Customer customer);
}