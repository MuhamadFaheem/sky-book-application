package com.miniproject.skybook.model.dto.response;


import com.miniproject.skybook.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RegisterResponseDTO {
    private String email;
    private ERole role;
}
