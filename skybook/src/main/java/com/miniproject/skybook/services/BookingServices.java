package com.miniproject.skybook.services;


import com.miniproject.skybook.model.dto.BookingDTO;
import com.miniproject.skybook.model.dto.response.BookingResDTO;
import com.miniproject.skybook.model.entity.Booking;

public interface BookingServices {
    Booking createBooking(BookingDTO booking);
    BookingResDTO getBookingById(String id);
}
