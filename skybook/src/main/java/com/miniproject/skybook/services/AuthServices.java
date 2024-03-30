package com.miniproject.skybook.services;

import com.miniproject.skybook.model.dto.request.AuthRequestDTO;
import com.miniproject.skybook.model.dto.response.AuthLoginResponseDTO;
import com.miniproject.skybook.model.dto.response.AuthRegistResponseDTO;

public interface AuthServices {
    AuthRegistResponseDTO registerAdmin(AuthRequestDTO authRequestDTO);

    AuthLoginResponseDTO login(AuthRequestDTO authRequestDTO);
}
