package com.github.bakery.cesco.week05;

import java.time.OffsetDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * Hint createAuthentication override
 */
public class CustomAnonymousAuthenticationFilter extends AnonymousAuthenticationFilter {

    public CustomAnonymousAuthenticationFilter(String key) {
        super(key);
    }

    public CustomAnonymousAuthenticationFilter(String key, Object principal,
                                               List<GrantedAuthority> authorities) {
        super(key, principal, authorities);
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest request) {
        final Authentication authentication = super.createAuthentication(request);
        return new CustomAnonymousAuthenticationToken(OffsetDateTime.now(), authentication);
    }
}
