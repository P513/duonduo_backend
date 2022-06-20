package com.gg.duonduo.service;

import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> userList() {
        System.out.println(userMapper.userList());
        System.out.println("유저리스트 출력 성공");
        return userMapper.userList();
    }

    @Transactional
    public void insertUser(UserDto user) {
        System.out.println("유저 DB 저장 성공");
        System.out.println(passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userMapper.insertUser(user);
    }

    public UserDto fetchUserByID(int id) {
        System.out.println(userMapper.fetchUserByID(id));
        System.out.println("유저 ID로 조회 성공");
        return userMapper.fetchUserByID(id);
    }

    @Transactional
    public void updateUser(UserDto updateUser) {
        System.out.println("유저 DB 갱신 성공");
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userMapper.updateUser(updateUser);
    }

    public void deleteUser(int id) {
        System.out.println("유저 DB 삭제 성공");
        userMapper.deleteUser(id);
    }

    public UserDto loginByEmail(UserDto user){
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println(userMapper.fetchUserByEmail(email));
        UserDto userDto = userMapper.fetchUserByEmail(email);
        try{
        if (userDto == null) {
            throw new EmailNotExistException();
        }
        // 비밀번호가 일치하면
        if(passwordEncoder.encode(user.getPassword()) != userDto.getPassword()) {
            throw new PasswordNotMatchedException();
        }
        }
        catch(EmailNotExistException e){
            System.err.println("Email Does Not Exist.");
        }
        catch(PasswordNotMatchedException e){
            System.err.println("Password Does Not Matched.");
        }
        System.out.println("유저 Email로 로그인 성공");
        return userDto;
    }

    private class PasswordNotMatchedException extends Exception {
    }

    private class EmailNotExistException extends Exception {
    }
}
