package com.gg.duonduo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*
    DTO : 계층 간 데이터 객체
 */
@Data
public class UserDto implements UserDetails {

    private long id;
    private long nicknameId;
    private String role;
    private String password;
    private String email;
    private String naverOAuth;
    private String kakaoOAuth;
    private int evalCnt;
    private int evalSum;
    @JsonFormat(pattern="yyyyMMddHHmmss")
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

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