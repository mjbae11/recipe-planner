package org.michaelbae.recipeplanner.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true; // Used for Spring Security

    public enum Role {
        BASEUSER,
        ADMIN
    }
}