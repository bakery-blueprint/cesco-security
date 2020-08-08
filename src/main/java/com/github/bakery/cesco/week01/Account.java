package com.github.bakery.cesco.week01;

import lombok.Data;

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
}
