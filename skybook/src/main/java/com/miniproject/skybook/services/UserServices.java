package com.miniproject.skybook.services;

import com.miniproject.skybook.model.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    AppUser loadUserbyUserId(String userId);
}
