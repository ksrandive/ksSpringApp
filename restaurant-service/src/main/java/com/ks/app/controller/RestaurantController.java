package com.ks.app.controller;

import com.ks.app.model.Restaurant;
import com.ks.app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rest")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Restaurant>> getRestaurantName() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping(value = "/type/{type}")
    public ResponseEntity<List<Restaurant>> getCuisineTypeRestaurantName(@PathVariable(value = "type") String cuisineType) {
        return new ResponseEntity<>(restaurantService.getCuisineTypeRestaurant(cuisineType), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Restaurant> getRestaurantNameById(@PathVariable(value = "id") Integer restaurantId) {
        return new ResponseEntity<>(restaurantService.getByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addRestaurantName(@RequestBody Restaurant restaurant) {
        Restaurant addRestaurant = restaurantService.addRestaurant(restaurant);
        if(addRestaurant == null){
            return new ResponseEntity<>("User not registered as restaurant user", HttpStatus.OK);
        }
        return  new ResponseEntity<>(addRestaurant, HttpStatus.OK);
    }
}
