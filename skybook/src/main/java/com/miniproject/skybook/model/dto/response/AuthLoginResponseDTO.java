package com.miniproject.skybook.model.dto.response;

import com.miniproject.skybook.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AuthLoginResponseDTO {
    private String email;
    private String roles;
    private String token;
}
