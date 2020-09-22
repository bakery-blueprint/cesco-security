package com.github.bakery.cesco.week05;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.time.OffsetDateTime;
import java.util.Collection;

public class CustomAnonymousAuthenticationToken extends AnonymousAuthenticationToken {

    public OffsetDateTime offsetDateTime;

    public CustomAnonymousAuthenticationToken(OffsetDateTime offsetDateTime, String key, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(key, principal, authorities);
    }
}
