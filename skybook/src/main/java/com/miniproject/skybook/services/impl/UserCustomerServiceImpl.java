package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.constant.ERole;
import com.miniproject.skybook.model.dto.request.CustomerUserAuthRequestDTO;
import com.miniproject.skybook.model.dto.response.RegisterResponseDTO;
import com.miniproject.skybook.model.entity.Customer;
import com.miniproject.skybook.model.entity.Role;
import com.miniproject.skybook.model.entity.User;
import com.miniproject.skybook.repository.UserRepository;
import com.miniproject.skybook.services.CustomerServices;
import com.miniproject.skybook.services.UserCustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCustomerServiceImpl implements UserCustomerServices {
    private final RoleServiceImpl roleService;
    private final CustomerServices customerService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public RegisterResponseDTO registerCustomer(CustomerUserAuthRequestDTO customerUserAuthRequestDTO) {
        try {
            Role role = roleService.getOrsaveRole(ERole.ROLE_CUSTOMER);
            List<Role> roles = new ArrayList<>();
            roles.add(role);

            String userId = "user_customer"+ NanoIdUtils.randomNanoId();
            User user = User.builder()
                    .id(userId)
                    .email(customerUserAuthRequestDTO.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(customerUserAuthRequestDTO.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);

            String customerId = "customer-"+ NanoIdUtils.randomNanoId();
            Customer customer = Customer.builder()
                    .id(customerId)
                    .firstName(customerUserAuthRequestDTO.getFirstName())
                    .lastName(customerUserAuthRequestDTO.getLastName())
                    .dateOfBirth(customerUserAuthRequestDTO.getDateOfBirth())
                    .phone(customerUserAuthRequestDTO.getPhone())
                    .deleted(false)
                    .user(user)
                    .build();
            customerService.saveCustomer(customer);
            return RegisterResponseDTO.builder()
                    .email(user.getEmail())
                    .role(role.getRole())
                    .build();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
