package com.miniproject.skybook.model.dto;

import com.miniproject.skybook.constant.EClass;
import com.miniproject.skybook.constant.ETrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDTO {
    private EClass bookClass;
    private ETrip trip;
    private List<BookingFlightDTO> flight;
}
