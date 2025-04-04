package com.ks.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private Integer customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;
    private LocalDateTime updatedTs;
    private String updatedBy;

}
