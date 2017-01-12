package com.lhz.hongbaoapp.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by lhz on 16-9-5.
 */
public class Role implements GrantedAuthority {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name;
    }
}
