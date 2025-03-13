package org.michaelbae.recipeplanner.repository;

import org.michaelbae.recipeplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // For Spring Security

    boolean existsByEmail(String email);
}