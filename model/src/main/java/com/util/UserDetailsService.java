package com.user_manager_service.security.services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsService implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userType;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsService(Long userId, String userType, String email, String password,
                              Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsService build(UserVO user, Collection<? extends SimpleGrantedAuthority> authorities) {
        return new UserDetailsService(
            user.getUserId(),
            user.getUserType(),
            user.getEmail(),
            user.getPassword(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
    }

    public Long getUserId() {
      return userId;
    }

    public String getEmail() {
      return email;
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public String getUsername() {
      return userType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
          return true;
        if (o == null || getClass() != o.getClass())
          return false;
        UserDetailsService user = (UserDetailsService) o;
        return Objects.equals(userId, user.userId);
    }
}
