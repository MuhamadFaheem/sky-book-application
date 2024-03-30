package com.miniproject.skybook.controller;

import com.miniproject.skybook.constant.ConstantAPIPath;
import com.miniproject.skybook.model.dto.BookingDTO;
import com.miniproject.skybook.model.dto.BookingDetailDTO;
import com.miniproject.skybook.model.dto.response.BookingResDTO;
import com.miniproject.skybook.model.entity.Booking;
import com.miniproject.skybook.services.BookingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstantAPIPath.BOOKING)
public class BookController {

    private final BookingServices bookingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO booking){
        try {
            Booking result = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<BookingResDTO> getBooking(@PathVariable String id){
        try {
            BookingResDTO result = bookingService.getBookingById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
