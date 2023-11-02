package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.user.Permission;
import com.khacv.hotelbookingapp.user.Role;
import com.khacv.hotelbookingapp.user.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfoDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserInfoDetails(UserInfo userInfo) {
        username = userInfo.getUsername();
        password = userInfo.getPassword();
//        authorities = userInfo.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
//                .collect(Collectors.toList());

        authorities = new ArrayList<>();
        for (Role role : userInfo.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName()));
            for (Permission permission : role.getPermissions()){
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
