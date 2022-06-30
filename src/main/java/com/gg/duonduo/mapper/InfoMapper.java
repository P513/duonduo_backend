package com.gg.duonduo.mapper;

import com.gg.duonduo.domain.InfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper {
    InfoDto fetchInfoByUserId(Long id);

    void insertInfo(InfoDto infoDto);

    void updateInfo(long userId, InfoDto infoDto);
}
