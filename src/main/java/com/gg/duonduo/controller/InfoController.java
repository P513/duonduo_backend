package com.gg.duonduo.controller;

import com.gg.duonduo.config.Response;
import com.gg.duonduo.domain.InfoDto;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.service.AuthService;
import com.gg.duonduo.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<Response<Object>> retrieveInfo(@RequestHeader("Authorization") String authorization) {
        Long id = getIdFromDecodedToken(authorization);
        InfoDto infoDto = infoService.fetchInfoByUserId(id);
        System.out.println(infoDto);
        if(infoDto == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null,false,"회원 정보를 먼저 등록해주세요."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(infoDto,true,"회원 정보를 조회합니다."));
    }

    @PatchMapping
    public ResponseEntity<Response<Object>> updateInfo(@RequestHeader("Authorization") String authorization, @RequestBody InfoDto info) {
        Long id = getIdFromDecodedToken(authorization);
        infoService.updateInfo(id, info);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null,true,"회원 정보를 조회합니다."));
    }

    @PostMapping
    public ResponseEntity<Response<Object>> insertInfo(@RequestHeader("Authorization") String authorization, @RequestBody InfoDto info) {
        Long id = getIdFromDecodedToken(authorization);
        System.out.println(info);
        InfoDto infoDto = infoService.insertInfo(id, info);
        System.out.println(infoDto);
        if(infoDto == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(null,false,"회원 정보를 수정해주세요."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(null,true,"회원 정보를 조회합니다."));
    }

    private Long getIdFromDecodedToken(@RequestHeader("Authorization") String authorization) {
        String token = authorization;
        Long id = authService.decode(token);
        return id;
    }

}
