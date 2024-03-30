package com.miniproject.skybook.controller;

import com.miniproject.skybook.constant.ConstantAPIPath;
import com.miniproject.skybook.model.dto.FlightDTO;
import com.miniproject.skybook.model.entity.Flight;
import com.miniproject.skybook.services.FlightServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstantAPIPath.API+ConstantAPIPath.FLIGHT)
public class FlightController {
    private final FlightServices flightService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Flight> createFlights(@RequestBody FlightDTO flightDTO){
        try {
            Flight result = flightService.saveFlight(flightDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Flight> getFlightById(@PathVariable String id){
        Flight result = flightService.getFlightbyId(id);
        if(result!=null){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Flight> deleteFlightById(@PathVariable String id){
        Flight result = flightService.getFlightbyId(id);
        if(result!=null){
            flightService.deleteFlightbyId(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Flight> updateFlightById(@RequestBody Flight flight){
        Flight result = flightService.updateFlightbyId(flight);
        if(result!=null){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}