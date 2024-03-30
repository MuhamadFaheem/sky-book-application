package com.miniproject.skybook.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ms_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@ToString
public class User {
    @Id
    @Column(nullable = false, name = "user_id")
    private String id;
    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trx_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}