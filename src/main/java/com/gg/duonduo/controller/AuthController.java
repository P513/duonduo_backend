package com.gg.duonduo.controller;

import com.gg.duonduo.config.Response;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 회원탈퇴 API
    @DeleteMapping("/resign")
    public ResponseEntity<Response> deleteUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization;
        Long id = authService.decode(token);
        authService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null, true, "회원 삭제를 성공하였습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDto user) {
        String token = authService.loginByEmail(user);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(null, false, "로그인을 실패하였습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(token, true, "로그인을 성공하였습니다."));
    }

    // 비밀번호 변경 API
    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestHeader("Authorization") String authorization, @RequestBody String password) {
        String token = authorization;
        Long id = authService.decode(token);
        UserDto user = authService.fetchUserByID(id);
        authService.updateUser(user, password);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null, true, "비밀번호 변경을 성공하였습니다."));
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<Response<Object>> insertUser(@RequestBody UserDto user) {
        boolean isSuccess = authService.insertUser(user);
        if (isSuccess == false) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null, false, "회원가입을 실패했습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(null, true, "회원가입을 완료했습니다."));
        }
    }

    @PostMapping("/token")
    public ResponseEntity<Object> verifyToken(@RequestBody String token) {
        Long tokenData = authService.decode(token);
        if (tokenData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null, false, "토큰이 유효하지 않습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(tokenData, true, "토큰이 유효합니다."));
    }


}
