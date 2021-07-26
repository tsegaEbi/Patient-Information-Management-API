package com.therapy.therapy.user;

import com.therapy.therapy.user.roles.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class UserDetail implements UserDetails {
    private String userName;
    private String password;
    private boolean enabled;
    private List<Role>roles;

    public UserDetail(String userName, String password,boolean enabled, List<Role>roles){
       this.userName =userName;
       this.password=password;
       this.enabled=enabled;
       this.roles=roles;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();


        if(this.roles!=null) {
            for (Role role : this.roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

        }
        else
        {
            throw new IllegalArgumentException("No roles");
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return this.enabled;
    }

}
