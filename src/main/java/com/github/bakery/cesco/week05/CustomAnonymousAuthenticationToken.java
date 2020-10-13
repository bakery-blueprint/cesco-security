package com.github.bakery.cesco.week05;

import java.time.OffsetDateTime;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

public class CustomAnonymousAuthenticationToken extends AbstractAuthenticationToken {

    private final Authentication token;
    private final OffsetDateTime offsetDateTime;

    public CustomAnonymousAuthenticationToken(OffsetDateTime offsetDateTime, Authentication token) {
        super(token.getAuthorities());
        this.token = token;
        this.offsetDateTime = offsetDateTime;
    }

    @Override
    public Object getCredentials() {
        return token.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return token.getPrincipal();
    }
}
