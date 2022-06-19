package com.gg.duonduo.domain;

import lombok.Data;

/*
    DTO : 계층 간 데이터 객체
 */
@Data
public class UserDto {

    int id;
    int nicknameId;
    String password;
    String salt;
    String email;
    String naverOAuth;
    String kakaoOAuth;
    int evalCnt;
    int evalSum;
    String createdAt;
    String updatedAt;
    String deletedAt;

}