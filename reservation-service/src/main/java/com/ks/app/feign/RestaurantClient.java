package com.ks.app.feign;

import com.ks.app.model.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RESTAURANT-SERVICE", path = "/rest")
public interface RestaurantClient {

    @GetMapping(value = "/all")
    ResponseEntity<List<Restaurant>> getRestaurantName();

    @GetMapping(value = "/type/{type}")
    ResponseEntity<List<Restaurant>> getCuisineTypeRestaurantName(@PathVariable(value = "type") String cuisineType);

    @GetMapping(value = "/id/{id}")
    ResponseEntity<Restaurant> getRestaurantNameById(@PathVariable(value = "id") Integer restaurantId);

    @PostMapping(value = "/add")
    ResponseEntity<Restaurant> addRestaurantName(@RequestBody Restaurant restaurant);
}
