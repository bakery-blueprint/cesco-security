package com.github.bakery.cesco.week07;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    @Secured("ROLE_USER")
    public void securedService() {

    }

    @RolesAllowed("ROLE_USER")
    public void rolesAllowedService() {

    }

}
