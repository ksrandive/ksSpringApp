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
            "/actuator/**"
    );

    public Predicate<ServerHttpRequest> isSecure =
            serverHttpRequest ->
                    apis.stream().noneMatch(url -> serverHttpRequest.getURI().getPath().contains(url));
}
