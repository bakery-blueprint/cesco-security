package com.github.bakery.cesco.week01;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                                .map(account -> User.builder()
                                                    .username(account.getUsername())
                                                    .password(account.getPassword())
                                                    .roles(account.getRole().name())
                                                    .build())
                                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Account save(final Account account) {
        return accountRepository.save(account);
    }

    public Account findById(final Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot found account id : " + id));
    }

    @PostConstruct
    public void init() {
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);
        save(account);
    }
}
