package com.yiyuclub.springsecurity.config;

import com.yiyuclub.springsecurity.models.IdeaRoleModel;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserInfo implements UserDetails {
    private String password;

    private String username;

    private List<IdeaRoleModel> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set authoritiesSet = new HashSet();

        for (IdeaRoleModel r:roles){
            authoritiesSet.add(new SimpleGrantedAuthority(r.getRoleName()));
        }
        return authoritiesSet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
