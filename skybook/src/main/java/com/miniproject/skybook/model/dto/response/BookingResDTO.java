package com.miniproject.skybook.model.dto.response;

import com.miniproject.skybook.constant.EClass;
import com.miniproject.skybook.constant.ETrip;
import com.miniproject.skybook.model.dto.BookingDetailDTO;
import com.miniproject.skybook.model.entity.BookingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingResDTO {
    private String bookingId;
    private Date bookingDate;
    private String customerId;
    private List<BookingDetailDTO> bookingDetail;
}
