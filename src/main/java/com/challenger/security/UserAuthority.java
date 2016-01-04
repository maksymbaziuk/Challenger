package com.challenger.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Maksym_Baziuk on 15.12.2015.
 */
public enum UserAuthority implements GrantedAuthority {
    USER, ANONYMOUS, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
