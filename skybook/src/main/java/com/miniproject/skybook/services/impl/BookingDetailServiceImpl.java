package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.model.entity.BookingDetail;
import com.miniproject.skybook.repository.BookingDetailRepository;
import com.miniproject.skybook.services.BookingDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingDetailServiceImpl implements BookingDetailServices {
    private final BookingDetailRepository bookingDetailRepository;
    @Override
    public BookingDetail saveBookingDetail(BookingDetail bookingDetail) {
        String purchaseId = "booking_detail-"+ NanoIdUtils.randomNanoId();
        bookingDetail.setId(purchaseId);
        return bookingDetailRepository.save(bookingDetail);

    }
}