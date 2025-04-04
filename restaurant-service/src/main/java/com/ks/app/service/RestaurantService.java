package com.ks.app.service;

import com.ks.app.feign.CustomerClient;
import com.ks.app.model.Customer;
import com.ks.app.model.Restaurant;
import com.ks.app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CustomerClient customerClient;


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        ResponseEntity<Customer> customer = customerClient.getCustomerById(restaurant.getCustomerId());

        if("RS_USER".equals(customer.getBody().getRole())){
            restaurant.setIsActive(1);
            restaurant.setUpdatedBy(customer.getBody().getRole());
            restaurant.setUpdatedTs(LocalDateTime.now());
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    public List<Restaurant> getCuisineTypeRestaurant(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

    public Restaurant getByRestaurantId(Integer restaurantId){
        return restaurantRepository.findById(restaurantId).get();
    }
}
