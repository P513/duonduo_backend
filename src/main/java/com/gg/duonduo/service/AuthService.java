package com.gg.duonduo.service;

import com.gg.duonduo.config.JwtToken;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final JwtToken jwtToken;

    @Transactional
    public boolean insertUser(UserDto user) {
        UserDto userDto = authMapper.fetchUserByEmail(user.getEmail());
        if (userDto == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("유저 DB 저장 시도..");
            authMapper.insertUser(user);
            return true;
        } else {
            System.out.println("해당 이메일이 이미 존재합니다.");
            return false;
        }
    }

    public void deleteUser(long id) {
        System.out.println("유저 DB 삭제 시도..");
        authMapper.deleteUser(id);
    }

    public UserDto fetchUserByID(long id) {
        System.out.println(authMapper.fetchUserByUserID(id));
        System.out.println("유저 ID로 조회 시도..");
        return authMapper.fetchUserByUserID(id);
    }

    public Long decode(String token) {
        System.out.println("토큰 검증 중..");
        return jwtToken.decode(token);
    }

    public String loginByEmail(UserDto user) {
        String email = user.getEmail();
        UserDto userDto = authMapper.fetchUserByEmail(email);
        try {
            if (userDto == null) {
                throw new EmailNotExistException();
            }
            // 비밀번호가 일치하면
            if (!passwordEncoder.matches(user.getPassword(), userDto.getPassword())) {
                throw new PasswordNotMatchedException();
            }
        } catch (EmailNotExistException e) {
            System.err.println("Email Does Not Exist.");
            return null;
        } catch (PasswordNotMatchedException e) {
            System.err.println("Password Does Not Match.");
            return null;
        }
        System.out.println("유저 Email로 로그인 시도..");
        final JwtToken.TokenRes tokenDto = new JwtToken.TokenRes(jwtToken.createToken(userDto.getId()));
        return tokenDto.getToken();
    }

    @Transactional
    public void updateUser(UserDto updateUser, String password) {
        updateUser.setPassword(passwordEncoder.encode(password));
        System.out.println("유저 DB 갱신 시도..");
        System.out.println(password+" "+ updateUser.getPassword());
        authMapper.updateUser(updateUser);
    }

    private class PasswordNotMatchedException extends Exception {
    }

    private class EmailNotExistException extends Exception {
    }
}
