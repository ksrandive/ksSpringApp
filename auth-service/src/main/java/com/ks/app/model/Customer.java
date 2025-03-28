package com.ks.app.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private Integer customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;
}
