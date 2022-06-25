package com.gg.duonduo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/*
    DTO : 계층 간 데이터 객체
 */
@Data
public class UserDto {

    int id;
    int nicknameId;
    String password;
    String email;
    String naverOAuth;
    String kakaoOAuth;
    int evalCnt;
    int evalSum;
    @JsonFormat(pattern="yyyyMMddHHmmss")
    String createdAt;
    String updatedAt;
    String deletedAt;

}