package com.github.bakery.cesco.week07;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class MethodSecurity {
//    @Override
//    protected AccessDecisionManager accessDecisionManager() {
//        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//        final AffirmativeBased affirmativeBased = (AffirmativeBased) super.accessDecisionManager();
//        affirmativeBased.getDecisionVoters().add(new RoleHierarchyVoter(roleHierarchy));
//        return affirmativeBased;
//    }
}
