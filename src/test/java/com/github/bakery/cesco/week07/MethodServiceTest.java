package com.github.bakery.cesco.week07;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class MethodServiceTest {
    private final MethodService methodService;
    private final AuthenticationManager authenticationManager;

    @Test
    void service() {
        // given
        final UserDetails userDetails = mock(UserDetails.class);
        final String password = "123";
        final Authentication token = new UsernamePasswordAuthenticationToken(userDetails, password);

        // when
        authenticationManager.authenticate(token);

        // then
        methodService.service();
    }
}