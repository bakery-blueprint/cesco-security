package com.github.bakery.cesco.week07;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    @Secured("ROLE_USER")
    public void service() {

    }
}
