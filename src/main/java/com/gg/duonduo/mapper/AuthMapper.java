package com.gg.duonduo.mapper;

import com.gg.duonduo.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    void insertUser(UserDto user);

    void updateUser(UserDto user);

    void deleteUser(long id);

    UserDto fetchUserByEmail(String email);
    UserDto fetchUserByUserID(long id);
}
