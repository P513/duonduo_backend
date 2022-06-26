package com.gg.duonduo.controller;

import java.util.List;

import com.gg.duonduo.config.JwtToken;
import com.gg.duonduo.config.Response;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseEntity<Response<List<UserDto>>> userList() {
        List<UserDto> userDtos = userService.userList();
        if (userDtos == null || userDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response<>(null, false, "등록된 사용자가 없습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>(userDtos, true, "사용자 목록을 불러왔습니다."));
        }
    }

    // 회원가입 API
    @PostMapping
    public ResponseEntity<Response<Object>> insertUser(@RequestBody UserDto user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(null, true, "회원가입을 완료했습니다."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Object>> fetchUserByID(@PathVariable int id) {
        UserDto userDto = userService.fetchUserByID(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null, false, "회원 조회에 실패하였습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(userDto, true, "회원 조회를 성공하였습니다."));
        }
    }

    // 비밀번호 변경 API
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable int id, @RequestBody UserDto user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null, true, "비밀번호 변경을 성공하였습니다."));
    }

    // 회원탈퇴 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null, true, "비밀번호 변경을 성공하였습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDto user) {
        String token = userService.loginByEmail(user);
        if(token==null){
            return ResponseEntity.status(HttpStatus.OK).body(new Response(null, false, "로그인을 실패하였습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(token, true, "로그인을 성공하였습니다."));
    }

    @PostMapping("/token")
    public ResponseEntity<Object> verifyToken(@RequestBody String token) {
        Long tokenData = userService.decode(token);
        if(tokenData==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null, false, "토큰이 유효하지 않습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(tokenData, true, "토큰이 유효합니다."));
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest httpServletRequest) {
        userService.logout(httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null, true, "로그아웃을 성공하였습니다."));
    }
}
