package com.ks.app.service;

import com.ks.app.entity.TableSlot;
import com.ks.app.feign.CustomerClient;
import com.ks.app.feign.RestaurantClient;
import com.ks.app.model.Customer;
import com.ks.app.entity.Reservation;
import com.ks.app.model.Restaurant;
import com.ks.app.repository.ReservationRepository;
import com.ks.app.repository.TableSlotRepository;
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

    @Autowired
    TableSlotRepository tableSlotRepository;

    public String saveBooking(Reservation reservation) {
        Customer customer = getCustomer(reservation.getCustomerId());
        Restaurant restaurant = getRestaurant(reservation.getRestaurantId());
        if(customer == null){
            return "Customer Not found, Please register the customer detail";
        } else if(restaurant == null){
            return "Restaurant Not found, Please register the restaurant detail";
        }else {
            //find booked tables for the booking date and restaurant

            Integer bookedTables = tableSlotRepository.findBookedTables(reservation.getRestaurantId(),
                    reservation.getReservationDate().toString());

            if(bookedTables >= restaurant.getTotalTables()){
                return String.format("Table not available for %s, please try another date",
                       reservation.getReservationDate().toString());
            }
            doBooking(reservation);
        }
        return String.format("%s your booking for hotel %s is done for total guest %s!",
                customer.getName(), restaurant.getName(), reservation.getTotalGuest());
    }

    private void doBooking(Reservation reservation) {
        reservation.setBookingStatus("SUCCESS");
        Reservation save = reservationRepository.save(reservation);
        tableSlotRepository.save(TableSlot.builder()
                .reservationId(save.getId())
                .reservationDate(save.getReservationDate())
                .restaurantId(save.getRestaurantId())
                .customerId(save.getCustomerId())
                .build());
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
