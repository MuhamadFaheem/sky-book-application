package com.miniproject.skybook.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDTO {
    private String customerId;
    private List<BookingDetailDTO> bookingDetails;
}
