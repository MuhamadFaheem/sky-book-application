package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.model.dto.BookingDTO;
import com.miniproject.skybook.model.dto.BookingDetailDTO;
import com.miniproject.skybook.model.dto.BookingFlightDTO;
import com.miniproject.skybook.model.dto.FlightDTO;
import com.miniproject.skybook.model.dto.response.BookingResDTO;
import com.miniproject.skybook.model.dto.response.GetFlightwithDTO;
import com.miniproject.skybook.model.entity.Booking;
import com.miniproject.skybook.model.entity.BookingDetail;
import com.miniproject.skybook.model.entity.Customer;
import com.miniproject.skybook.model.entity.Flight;
import com.miniproject.skybook.repository.BookingRepository;
import com.miniproject.skybook.repository.FlightRepository;
import com.miniproject.skybook.repository.UserRepository;
import com.miniproject.skybook.services.BookingDetailServices;
import com.miniproject.skybook.services.BookingServices;
import com.miniproject.skybook.services.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServicesImpl implements BookingServices {
    private final BookingRepository bookingRepository;
    private final CustomerServices customerServices;
    private final BookingDetailServices bookingDetailServices;
    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public Booking createBooking(BookingDTO booking) {
        String bookingId = "booking-"+ NanoIdUtils.randomNanoId();
        String bookingDetailId = "booking_detail-"+ NanoIdUtils.randomNanoId();
        Customer customer = customerServices.getCustomerbyId(booking.getCustomerId());
        System.out.println(customer);
        Booking bookingEntity = new Booking();
        bookingEntity.setId(bookingId);
        bookingEntity.setCustomer(customer);
        Booking savedBooking = bookingRepository.save(bookingEntity);
        List<BookingDetail> bookingDetailList = new ArrayList<>();
        for(BookingDetailDTO bookingDetail : booking.getBookingDetails()) {
            BookingDetail bookingDetailEntity = new BookingDetail();
            bookingDetailEntity.setBookClass(bookingDetail.getBookClass());
            bookingDetailEntity.setTrip(bookingDetail.getTrip());
            bookingDetailEntity.setFlight(flightRepository.findById(bookingDetail.getFlight().get(0).getId()).get());
            bookingDetailEntity.setPurchase(bookingEntity);
            Flight flight = flightRepository.findById(bookingDetail.getFlight().get(0).getId()).get();
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            BookingDetail temp = bookingDetailServices.saveBookingDetail(bookingDetailEntity);
            bookingDetailList.add(bookingDetailEntity);
            System.out.println(temp);
            bookingEntity.setPurchaseDetail(bookingDetailList);
        }
        bookingEntity.setPurchaseDetail(bookingDetailList);
        savedBooking = bookingRepository.save(bookingEntity);
        return bookingRepository.save(savedBooking);
    }

    @Override
    public BookingResDTO getBookingById(String id) {
        if (!bookingRepository.findById(id).isPresent()) {
            throw new RuntimeException("Data not found");
        }
        Booking booking = bookingRepository.findById(id).get();
        List<BookingDetailDTO> bookingDetailDTOList = new ArrayList<>();
        for(BookingDetail bookingDetail : booking.getPurchaseDetail()) {
            BookingDetailDTO bookingDetailDTO = new BookingDetailDTO();
            bookingDetailDTO.setBookClass(bookingDetail.getBookClass());
            bookingDetailDTO.setTrip(bookingDetail.getTrip());
            bookingDetailDTO.setFlight(List.of(
                    new BookingFlightDTO(
                    bookingDetail.getFlight().getId())));
            bookingDetailDTOList.add(bookingDetailDTO);
        }
        BookingResDTO bookingResDTO = new BookingResDTO();
        bookingResDTO.setBookingId(booking.getId());
        bookingResDTO.setBookingDate(booking.getBookingDate());
        bookingResDTO.setCustomerId(booking.getCustomer().getId());
        bookingResDTO.setBookingDetail(bookingDetailDTOList);
        return bookingResDTO;
    }
}
