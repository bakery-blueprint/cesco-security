package com.github.bakery.cesco.week02;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class Week02UserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("week02")) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                   .username(username)
                   .password("123").passwordEncoder(password -> NoOpPasswordEncoder.getInstance().encode(password))
                   .roles("ADMIN")
                   .build();
    }
}
