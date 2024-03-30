package com.miniproject.skybook.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserAuthRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String status;
    private Date dateOfBirth;
}