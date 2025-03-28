package com.ks.app.controller;

import com.ks.app.entity.Reservation;
import com.ks.app.model.Customer;
import com.ks.app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reserv")
public class ReservationController {


    @Autowired
    ReservationService reservationService;

    @PostMapping(value = "/hotel")
    public ResponseEntity<String> saveBooking(@RequestBody Reservation reservation){
      return new ResponseEntity<>(reservationService.saveBooking(reservation), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Reservation>> getAllBooking(){
        return new ResponseEntity<>(reservationService.getAllBooking(), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Customer> getReservationCustomer(@PathVariable Integer id){
        return new ResponseEntity<>(reservationService.getCustomer(id), HttpStatus.OK);
    }

}
