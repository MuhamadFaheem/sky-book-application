package com.miniproject.skybook.services;

import com.miniproject.skybook.model.dto.request.CustomerUserAuthRequestDTO;
import com.miniproject.skybook.model.dto.response.RegisterResponseDTO;

public interface UserCustomerServices {
    RegisterResponseDTO registerCustomer(CustomerUserAuthRequestDTO authRequestDTO);
}
