package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.constant.ERole;
import com.miniproject.skybook.model.dto.request.AuthRequestDTO;
import com.miniproject.skybook.model.dto.response.AuthLoginResponseDTO;
import com.miniproject.skybook.model.dto.response.AuthRegistResponseDTO;
import com.miniproject.skybook.model.entity.AppUser;
import com.miniproject.skybook.model.entity.Role;
import com.miniproject.skybook.model.entity.User;
import com.miniproject.skybook.repository.UserRepository;
import com.miniproject.skybook.security.JWTUtils;
import com.miniproject.skybook.services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServices {
    private final RoleServiceImpl roleService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public AuthRegistResponseDTO registerAdmin(AuthRequestDTO authRequestDTO) {
        try {
            Role role = roleService.getOrsaveRole(ERole.ROLE_ADMIN);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            String userId = "user_"+ NanoIdUtils.randomNanoId();
            User user = User.builder()
                    .id(userId)
                    .email(authRequestDTO.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(authRequestDTO.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            return AuthRegistResponseDTO.builder()
                    .email(user.getEmail())
                    .roles(role.getRole())
                    .build();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public AuthLoginResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword()
        ));
        AppUser appUser = (AppUser) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generatedToken(appUser);

        return AuthLoginResponseDTO.builder()
                .token(token)
                .email(appUser.getEmail())
                .roles(appUser.getRole().name())
                .build();
    }
}
