package com.miniproject.skybook.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Entity
@Table(name = "ms_customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@SQLDelete(sql = "UPDATE ms_customer set deleted = true where customer_id =?")
@SQLRestriction("deleted = false")
public class Customer {
    @Id
    @Column(nullable = false,name = "customer_id")
    private String id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    private Date dateOfBirth;
    @Column(nullable = false)
    private String phone;
    private String status;
    private Boolean deleted = Boolean.FALSE;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
