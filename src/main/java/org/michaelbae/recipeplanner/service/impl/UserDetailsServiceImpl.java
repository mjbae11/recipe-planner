package org.michaelbae.recipeplanner.service.impl;

import org.michaelbae.recipeplanner.model.User;
import org.michaelbae.recipeplanner.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails userDetails = (UserDetails) userRepository.findByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("No user found with the given email.");
        }
        return userDetails;
    }

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use!");
        }

        // Encode the password using PasswordEncoder
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);  // Set the encoded password

        // Save the user with the encoded password
        userRepository.save(user);
    }
}
