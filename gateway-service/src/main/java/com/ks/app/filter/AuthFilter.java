package com.ks.app.filter;

import com.ks.app.error.UserTokenException;
import com.ks.app.model.Token;
import com.ks.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter() {
        super(Config.class);
    }

    @ExceptionHandler({UserTokenException.class})
    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecure.test(exchange.getRequest())){
                //header contain token
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new UserTokenException(HttpStatus.BAD_REQUEST);
                }
                String header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(header != null && header.startsWith("Bearer ")){
                    header = header.substring(7);
                }

                 //rest call to validate
//               restTemplate.getForObject("http://AUTH-SERVICE/auth/validate?token="+header, String.class);
                    // Validate token and set authentication
                    Token object = restTemplate.getForObject("http://localhost:8111/auth/token?header=" + header, Token.class);
                    if(!jwtUtil.isValid(header, object)){
                        throw new UserTokenException(HttpStatus.UNAUTHORIZED);
                    }
            }
            return chain
                    .filter(exchange);
        }));
    }

    public static class Config{

    }
}
