package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.constant.ERole;
import com.miniproject.skybook.model.entity.Role;
import com.miniproject.skybook.repository.RoleRepository;
import com.miniproject.skybook.services.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleServices {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrsaveRole(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        //Role not empty and available in database
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        //Role not empty but not available
        String productId = "role-" + NanoIdUtils.randomNanoId();
        Role role1 = Role.builder()
                .id(productId)
                .role(role)
                .build();
        return roleRepository.saveAndFlush(role1);
    }
}