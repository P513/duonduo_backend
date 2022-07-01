package com.gg.duonduo.mapper;

import com.gg.duonduo.domain.InfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InfoMapper {
    InfoDto fetchInfoByUserId(Long id);

    void insertInfo(InfoDto infoDto);

    void updateInfo(@Param("userId") long userId, @Param("infoDto") InfoDto infoDto);
}
