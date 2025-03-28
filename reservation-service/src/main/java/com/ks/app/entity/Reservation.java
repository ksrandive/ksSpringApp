package com.ks.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer reservationId;
    private Integer customerId;
    private Integer restaurantId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate reservationDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime reservationFrom;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime reservationTo;

    private Integer totalGuest;
}
