package com.ks.app.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> apis = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka",
            "/auth/login",
            "/auth/logout",
            "/actuator/**"
    );

    public Predicate<ServerHttpRequest> isSecure =
            serverHttpRequest ->
                    apis.stream().noneMatch(url -> serverHttpRequest.getURI().getPath().contains(url));

    public boolean isSecureApi(ServerHttpRequest serverHttpRequest){
        if(serverHttpRequest.getHeaders().containsKey("role")){
            return "ADMIN_USER".equals(Objects.requireNonNull(serverHttpRequest.getHeaders().get("role")).get(0));
        }
        return false;
    }
}
