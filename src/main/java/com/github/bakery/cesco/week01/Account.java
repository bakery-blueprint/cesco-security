package com.github.bakery.cesco.week01;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@Entity
public class Account {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Account encodingPassword(PasswordEncoder encoder) {
        setPassword(encoder.encode(password));
        return this;
    }
}
