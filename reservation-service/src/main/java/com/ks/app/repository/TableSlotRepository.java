package com.ks.app.repository;

import com.ks.app.entity.TableSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableSlotRepository extends JpaRepository<TableSlot, Integer> {

    @NativeQuery(value = "select count(*) from table_slot where restaurant_id = :restaurantId and reservation_date = to_date(:reservationDate, 'YYYY-MM-DD')")
    Integer findBookedTables(@Param("restaurantId") Integer restaurantId, @Param("reservationDate") String reservationDate);
}
