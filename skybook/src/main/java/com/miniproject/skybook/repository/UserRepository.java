package com.miniproject.skybook.repository;

import com.miniproject.skybook.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM ms_user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
