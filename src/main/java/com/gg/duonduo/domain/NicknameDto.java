package com.gg.duonduo.domain;

import lombok.Data;

@Data
public class NicknameDto {

    int id;
    int userId;
    String name;
    int tier;
    int rank;
    String ment;
    int selfPos;
    int duoPos;
    int playStyle;
    int voice;
    boolean status;

}
