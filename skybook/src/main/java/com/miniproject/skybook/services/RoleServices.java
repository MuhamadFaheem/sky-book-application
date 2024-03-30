package com.miniproject.skybook.services;

import com.miniproject.skybook.constant.ERole;
import com.miniproject.skybook.model.entity.Role;

public interface RoleServices {
    Role getOrsaveRole(ERole role);
}
