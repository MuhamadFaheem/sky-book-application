package com.miniproject.skybook.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniproject.skybook.constant.EClass;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ms_flight")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@ToString
public class Flight {
    @Id
    @Column(nullable = false,name = "flight_id")
    private String id;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    private Integer totalSeats;
    @Column(nullable = false)
    private Integer availableSeats;
    @Column(nullable = false)
    private Double price;
}