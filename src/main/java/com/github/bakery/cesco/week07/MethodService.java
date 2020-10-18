package com.github.bakery.cesco.week07;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    @Secured("ROLE_USER")
    public void secured() {

    }

    @RolesAllowed("ROLE_USER")
    public void rolesAllowed() {

    }

    @PreAuthorize("#name == authentication.principal.username")
    public void preAuthorize(String name) {
    }

    @PostAuthorize("hasRole(USER)")
    public void postAuthorize(String name) {
    }
}
