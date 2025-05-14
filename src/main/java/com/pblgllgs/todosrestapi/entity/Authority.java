package com.pblgllgs.todosrestapi.entity;
/*
 *
 * @author pblgl
 * Created on 12-05-2025
 *
 */

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class Authority implements GrantedAuthority {
    private String authority;

    public Authority() {}
    public Authority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
