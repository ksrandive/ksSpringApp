package com.ks.app.filter;

import com.ks.app.error.UserTokenException;
import com.ks.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

//    @Autowired
//    private RestTemplate restTemplate;

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
                    throw new RuntimeException("missing authorization header.");
                }

                String header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(header != null && header.startsWith("Bearer ")){
                    header = header.substring(7);
                }

                try{
                 //rest call to validate
//                    restTemplate.getForObject("http://AUTH-SERVICE/auth/validate?token="+header, String.class);
                    jwtUtil.validateToken(header);
                }catch (Exception exception){
                    throw new RuntimeException("invalid token");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }
}
