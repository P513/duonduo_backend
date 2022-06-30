package com.gg.duonduo.service;

import com.gg.duonduo.domain.InfoDto;
import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.InfoMapper;
import com.gg.duonduo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoMapper infoMapper;
    private final UserMapper userMapper;

    public InfoDto fetchInfoByUserId(long id) {
        System.out.println(infoMapper.fetchInfoByUserId(id));
        System.out.println("유저 ID로 정보 조회 시도..");
        return infoMapper.fetchInfoByUserId(id);
    }

    @Transactional
    public InfoDto insertInfo(long userId, InfoDto info) {
        InfoDto infoDto = infoMapper.fetchInfoByUserId(userId);
        UserDto userDto = userMapper.fetchUserByUserID(userId);
        if(userDto == null){
            return null;
        }
        System.out.println("Info 등록 중..");
        if (infoDto == null) {
            info.setUserId(userDto.getId());
            System.out.println("Info userId 등록 중..");
            infoMapper.insertInfo(info);
            InfoDto createdInfoDto = infoMapper.fetchInfoByUserId(userId);
            System.out.println("insertInfo 중..");
            userMapper.updateInfoId(userDto.getId(),createdInfoDto.getId());
            System.out.println("updateInfoId 중..");
            return info;
        }
        else {
            System.out.println("해당 아이디의 정보가 이미 존재합니다.");
            return null;
        }
    }

    @Transactional
    public void updateInfo(Long userId, InfoDto info) {
        infoMapper.updateInfo(userId, info);
    }
}
