package com.miniproject.skybook.model.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerSearchDTO {
    private String customerFirstName;
    private String customerLastName;
    private Date customerDateOfBirth;
}