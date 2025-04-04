package com.ks.app.feign;

import com.ks.app.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="CUSTOMER-SERVICE", path = "/cust")
public interface CustomerClient {

    @GetMapping(value = "/all/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer customerId);

}
