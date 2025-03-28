package com.ks.app.repository;

import com.ks.app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findByCuisineType(String cuisineType);

    Restaurant findByRestaurantId(Integer restaurantId);
}
