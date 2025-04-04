package com.ks.app.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private Integer id;
    private String accessToken;
    private String refreshToken;
    private boolean loggedOut;
    private Integer refuserId;
}
