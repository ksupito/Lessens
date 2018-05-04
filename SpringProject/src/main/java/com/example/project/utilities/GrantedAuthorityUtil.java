package com.example.project.utilities;

import com.example.project.model.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class GrantedAuthorityUtil {
    private GrantedAuthorityUtil() {
    }

    public static Set<GrantedAuthority> getSetRoles() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add((UserRole.USER));
        return grantedAuthorities;
    }
}
