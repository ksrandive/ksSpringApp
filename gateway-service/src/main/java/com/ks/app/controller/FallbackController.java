package com.ks.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/custFallback")
    public Mono<String> customerFallback(){
        return Mono.just("Customer service is down, please try in sometime!");
    }

    @RequestMapping("/restFallback")
    public Mono<String> restaurantFallback(){
        return Mono.just("Restaurant service is down, please try in sometime!");
    }

    @RequestMapping("/reservFallback")
    public Mono<String> reservationFallback(){
        return Mono.just("Reservation service is down, please try in sometime!");
    }
}
