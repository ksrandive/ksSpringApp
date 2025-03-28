package com.ks.app.repository;

import com.ks.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer getByCustomerId(Integer customerId);
    Optional<Customer> findByName(String name);


}
