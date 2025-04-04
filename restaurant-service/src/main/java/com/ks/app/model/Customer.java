package com.ks.app.model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;

}
