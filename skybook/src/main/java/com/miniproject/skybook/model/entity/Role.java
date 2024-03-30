package com.miniproject.skybook.model.entity;

import com.miniproject.skybook.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
@ToString
public class Role {
    @Id
    @Column(name = "role_id",nullable = false)
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole role; // Enum
}
