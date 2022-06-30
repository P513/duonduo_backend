package com.gg.duonduo.controller;

import java.util.List;

import com.gg.duonduo.config.Response;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
    localhost:8080/users controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Response<List<UserDto>>> userList() {
        List<UserDto> userDtos = userService.userList();
        if (userDtos == null || userDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response<>(null, false, "등록된 사용자가 없습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>(userDtos, true, "사용자 목록을 불러왔습니다."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Object>> fetchUserByID(@PathVariable long id) {
        UserDto userDto = userService.fetchUserByUserID(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null, false, "회원 조회에 실패하였습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(userDto, true, "회원 조회를 성공하였습니다."));
        }
    }

}
