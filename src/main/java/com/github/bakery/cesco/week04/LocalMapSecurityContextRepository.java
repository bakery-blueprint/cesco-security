package com.github.bakery.cesco.week04;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

public class LocalMapSecurityContextRepository implements SecurityContextRepository {
    public static Map<String, SecurityContext> securityContexts = new HashMap<>();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        final String username = requestResponseHolder.getRequest().getParameter("username");
        return securityContexts.get(username);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        final String username = request.getParameter("username");
        securityContexts.put(username, context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        final String username = request.getParameter("username");
        return securityContexts.containsKey(username);
    }
}
