package com.github.bakery.cesco.week05;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.List;

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
}
