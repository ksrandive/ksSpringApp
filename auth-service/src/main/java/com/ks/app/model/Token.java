package com.ks.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accessToken;

    private String refreshToken;

    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "refuser_id")
    private UserCredential user;
}
