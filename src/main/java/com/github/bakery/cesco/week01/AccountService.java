package com.github.bakery.cesco.week01;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.bakery.cesco.week07.UserAccount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                                .map(UserAccount::new)
                                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Account save(final Account account) {
        account.encodingPassword(passwordEncoder);
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
