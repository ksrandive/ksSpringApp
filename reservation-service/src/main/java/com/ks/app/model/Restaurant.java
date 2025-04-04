package com.ks.app.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Restaurant {
    private Integer id;
    private Integer customerId;
    private String name;
    private String location;
    private String cuisineType;
    private Integer totalTables;
    private Integer isActive;
    private LocalDateTime updatedTs;
    private String updatedBy;
}
