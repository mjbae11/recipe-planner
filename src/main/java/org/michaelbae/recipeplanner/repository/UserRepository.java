package org.michaelbae.recipeplanner.repository;

import org.michaelbae.recipeplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // For Spring Security
}