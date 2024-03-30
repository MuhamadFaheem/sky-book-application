package com.miniproject.skybook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miniproject.skybook.constant.EClass;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_purchase")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Booking {
    @Id
    @Column(name = "booking_id")
    private String id;
    @CreationTimestamp
    private Date bookingDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "purchase")
    @JsonIgnoreProperties("purchase")
    private List<BookingDetail> purchaseDetail;
}
