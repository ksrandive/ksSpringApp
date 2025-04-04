package com.ks.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer customerId;
    private Integer restaurantId;
    private Integer totalGuest;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate reservationDate;

    private String bookingStatus;


//    @JsonFormat(pattern = "HH:mm")
//    private LocalTime reservationFrom;
//
//    @JsonFormat(pattern = "HH:mm")
//    private LocalTime reservationTo;

}
