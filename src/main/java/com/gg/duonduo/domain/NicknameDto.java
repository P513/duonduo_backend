package com.gg.duonduo.domain;

import lombok.Data;

@Data
public class NicknameDto {

    private long id;
    private long userId;
    private String name;
    private int tier;
    private int rank;
    private String ment;
    private int selfPos;
    private int duoPos;
    private int playStyle;
    private int voice;
    private boolean status;

}
