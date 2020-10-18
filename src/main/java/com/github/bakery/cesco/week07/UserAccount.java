package com.github.bakery.cesco.week07;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.github.bakery.cesco.week01.Account;

import lombok.Getter;

public class UserAccount extends User {

    @Getter
    private final Account account;

    public UserAccount(Account account) {
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole())));
        this.account = account;
    }
}
