package com.gg.duonduo.service;

import com.gg.duonduo.config.JwtToken;
import com.gg.duonduo.config.Response;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;
    private final JwtToken jwtToken;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, JwtToken jwtToken, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.jwtToken = jwtToken;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> userList() {
        System.out.println(userMapper.userList());
        System.out.println("유저리스트 출력 시도..");
        return userMapper.userList();
    }

    @Transactional
    public void insertUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("유저 DB 저장 시도..");
        userMapper.insertUser(user);
    }

    public UserDto fetchUserByID(int id) {
        System.out.println(userMapper.fetchUserByID(id));
        System.out.println("유저 ID로 조회 시도..");
        return userMapper.fetchUserByID(id);
    }

    @Transactional
    public void updateUser(UserDto updateUser) {
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        System.out.println("유저 DB 갱신 시도..");
        userMapper.updateUser(updateUser);
    }

    public void deleteUser(int id) {
        System.out.println("유저 DB 삭제 시도..");
        userMapper.deleteUser(id);
    }

    public Long decode(String token) {
        System.out.println("토큰 검증 중..");
        return jwtToken.decode(token);
    }

    public String loginByEmail(UserDto user) {
        String email = user.getEmail();
        UserDto userDto = userMapper.fetchUserByEmail(email);
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
        }catch (PasswordNotMatchedException e) {
            System.err.println("Password Does Not Match.");
            return null;
        }
        System.out.println("유저 Email로 로그인 시도..");
        final JwtToken.TokenRes tokenDto = new JwtToken.TokenRes(jwtToken.createToken(userDto.getId()));
        return tokenDto.getToken();
    }

    public void logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.fetchUserByEmail(username);
    }

    private class PasswordNotMatchedException extends Exception {
    }

    private class EmailNotExistException extends Exception {
    }
}
