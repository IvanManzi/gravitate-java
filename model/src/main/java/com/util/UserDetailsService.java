package com.util;


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

    private String jiraId;

    private String userType;

    private String username;

    private String[] adminPageAccess;

    private boolean isAccountNonLocked;

    private boolean hasSecurityQuestion;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsService(Long userId,String jiraId, String userType,String[] adminPageAccess, String username, String password,  boolean isAccountNonLocked, boolean hasSecurityQuestion,
                              Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.jiraId = jiraId;
        this.userType = userType;
        this.adminPageAccess = adminPageAccess;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonLocked = isAccountNonLocked;
        this.hasSecurityQuestion = hasSecurityQuestion;
    }

    public static UserDetailsService build(UserVO user,boolean hasSecurityQuestion, Collection<? extends SimpleGrantedAuthority> authorities) {
        return new UserDetailsService(
            user.getUserId(),
            user.getJiraId(),
            user.getUserType(),
            user.getAdminPageAccess(),
            user.getEmail(),
            user.getPassword(),
            user.isAccountNonLocked(),
            hasSecurityQuestion,
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
    }


    public String[] getAdminPageAccess(){
        return adminPageAccess;
    }
    public Long getUserId() {
      return userId;
    }

    public String getJiraId() {
        return jiraId;
    }

    public String getUserType() {
        return userType;
    }

    public boolean getAccountStatus(){
        return isAccountNonLocked;
    }

    public boolean isHasSecurityQuestion(){
        return hasSecurityQuestion;
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
      return getAccountStatus();
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
