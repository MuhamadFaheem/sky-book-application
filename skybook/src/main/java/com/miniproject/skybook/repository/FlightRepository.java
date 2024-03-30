package com.miniproject.skybook.repository;

import com.miniproject.skybook.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    @Query(value = "SELECT * FROM ms_flight WHERE flight_id = ?1", nativeQuery = true)
    Flight getFlightbyId(String id);
}
