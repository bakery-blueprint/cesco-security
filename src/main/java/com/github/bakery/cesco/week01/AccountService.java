package com.github.bakery.cesco.week01;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
