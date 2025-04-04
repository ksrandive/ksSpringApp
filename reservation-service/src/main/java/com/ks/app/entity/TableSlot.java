package com.ks.app.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Builder
public class TableSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer reservationId;
    private Integer restaurantId;
    private Integer customerId;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate reservationDate;

}
