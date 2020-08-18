package com.github.bakery.cesco.week01.woojin;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.bakery.cesco.week01.Role;

import lombok.Data;

@Data
@Entity
public class WAccount {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public WAccount encodingPassword(PasswordEncoder passwordEncoder) {
        setPassword(passwordEncoder.encode(getPassword()));
        return this;
    }
}
