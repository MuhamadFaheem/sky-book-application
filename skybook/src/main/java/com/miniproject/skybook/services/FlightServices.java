package com.miniproject.skybook.services;

import com.miniproject.skybook.model.dto.FlightDTO;
import com.miniproject.skybook.model.entity.Flight;

import java.util.List;

public interface FlightServices {
    Flight saveFlight(FlightDTO flightDTO);
    Flight getFlightbyId(String id);
    List<Flight> getAllFlights();
    Flight updateFlightbyId(Flight flight);
    String deleteFlightbyId(String id);
}
