package com.miniproject.skybook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String id;
    private String origin;
    private String destination;
    private Integer totalSeats;
    private Double price;
    private Integer availableSeats;
}
