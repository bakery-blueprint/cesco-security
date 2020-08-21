package com.github.bakery.cesco.week01.woojin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WAccountRepository extends JpaRepository<WAccount, Long> {
    Optional<WAccount> findByUsername(final String username);
}
