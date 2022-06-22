package com.gg.duonduo.controller;

import java.util.List;

import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.UserMapper;
import com.gg.duonduo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/*
    localhost:8080/users controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> userList() {
        return userService.userList();
    }

    // 회원가입 API
    @PostMapping
    public void insertUser(@RequestBody UserDto user) {
        userService.insertUser(user);
    }

    @GetMapping("/{id}")
    public UserDto fetchUserByID(@PathVariable int id) {
        return userService.fetchUserByID(id);
    }

    // 비밀번호 변경 API
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserDto user) {
        userService.updateUser(user);
    }

    // 회원탈퇴 API
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDto user, HttpServletRequest httpServletRequest){
        userService.loginByEmail(user, httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        userService.logout(httpServletRequest);
    }
}
