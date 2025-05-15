package com.pblgllgs.todosrestapi.util.impl;
/*
 *
 * @author pblgl
 * Created on 15-05-2025
 *
 */

import com.pblgllgs.todosrestapi.entity.User;
import com.pblgllgs.todosrestapi.util.FindAuthenticatedUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser {
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Authentication required");
        }
        return (User) authentication.getPrincipal();
    }
}
