package com.ks.app.service;

import com.ks.app.feign.CustomerClient;
import com.ks.app.feign.RestaurantClient;
import com.ks.app.model.Customer;
import com.ks.app.entity.Reservation;
import com.ks.app.model.Restaurant;
import com.ks.app.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    RestaurantClient restaurantClient;

    @Autowired
    RestTemplate restTemplate;

    public String saveBooking(Reservation reservation) {
        Customer customer = getCustomer(reservation.getCustomerId());
        Restaurant restaurant = getRestaurant(reservation.getRestaurantId());
        if(customer == null){
            return "Customer Not found, Please register the customer detail";
        } else if(restaurant == null){
            return "Restaurant Not found, Please register the restaurant detail";
        }else {
            reservationRepository.save(reservation);
        }
        return String.format("%s your booking for hotel %s is done for total guest %s!",
                customer.getName(), restaurant.getName(), reservation.getTotalGuest());
    }

    public Customer getCustomer(Integer customerId){
        return customerClient.getCustomerById(customerId).getBody();
//        return restTemplate.getForEntity("http://CUSTOMER-SERVICE/cust/all/"+customerId, Customer.class).getBody();
    }

    private Restaurant getRestaurant(Integer restaurantId){
        return restaurantClient.getRestaurantNameById(restaurantId).getBody();
    }

    public List<Reservation> getAllBooking() {
        return reservationRepository.findAll();
    }
}
