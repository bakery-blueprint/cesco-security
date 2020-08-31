package com.github.bakery.cesco.week01.woojin;

import com.github.bakery.cesco.week01.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

//@Service
@RequiredArgsConstructor
public class WAccountService implements UserDetailsService {
    private final WAccountRepository WAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return WAccountRepository.findByUsername(username)
                                 .map(account -> User.builder()
                                                     .username(account.getUsername())
                                                     .password(account.getPassword())
                                                     .roles(account.getRole().name())
                                                     .build())
                                 .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public WAccount save(final WAccount account) {
        return WAccountRepository.save(account);
    }

    public WAccount findById(final Long id) {
        return WAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot found account id : " + id));
    }

    @PostConstruct
    public void init() {
        final WAccount account = new WAccount();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);
        save(account);
    }
}
