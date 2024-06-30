package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query("SELECT r FROM Rental r WHERE r.car.id = :carId AND r.startDate <= :endDate AND r.endDate >= :startDate")
    List<Rental> findConflictingRentals(
            @Param("carId") int carId,
            @Param("endDate") LocalDate endDate,
            @Param("startDate") LocalDate startDate
    );

    @Query("SELECT r FROM Rental r WHERE r.car.id = :carId AND r.startDate <= :endDate AND r.endDate >= :startDate AND r.id <> :rentalId")
    List<Rental> findConflictingRentalsExcludingId(
            @Param("carId") int carId,
            @Param("endDate") LocalDate endDate,
            @Param("startDate") LocalDate startDate,
            @Param("rentalId") int rentalId
    );
}

