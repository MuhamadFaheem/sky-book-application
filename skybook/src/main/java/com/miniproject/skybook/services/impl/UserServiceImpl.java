package com.miniproject.skybook.services.impl;

import com.miniproject.skybook.model.entity.AppUser;
import com.miniproject.skybook.model.entity.User;
import com.miniproject.skybook.repository.UserRepository;
import com.miniproject.skybook.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    private final UserRepository userRepository;
    @Override
    public AppUser loadUserbyUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("invalid credential input"));
        return AppUser.builder()
                .id(user.getId())
                .email((user.getEmail()))
                .password((user.getPassword()))
                .role(user.getRoles().get(0).getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("invalid credential input"));
        return AppUser.builder()
                .id(user.getId())
                .email((user.getEmail()))
                .password((user.getPassword()))
                .role(user.getRoles().get(0).getRole())
                .build();
    }
}
