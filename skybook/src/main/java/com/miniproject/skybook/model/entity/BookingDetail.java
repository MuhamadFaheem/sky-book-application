package com.miniproject.skybook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miniproject.skybook.constant.EClass;
import com.miniproject.skybook.constant.ETrip;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trx_purchase_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookingDetail {
    @Id
    @Column(name = "purchase_detail_id")
    private String id;
    @Enumerated(EnumType.STRING)
    private EClass bookClass;
    @Enumerated(EnumType.STRING)
    private ETrip trip;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("purchaseDetail")
    private Booking purchase;
}