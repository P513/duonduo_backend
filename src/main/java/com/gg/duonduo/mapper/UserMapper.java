package com.gg.duonduo.mapper;

import java.util.List;

import com.gg.duonduo.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

/*
    DAO 느낌으로 DB에 접근해서 SQL Mapper
 */
@Mapper
public interface UserMapper {

    List<UserDto> userList();

    UserDto fetchUserByID(long id);

    UserDto fetchUserByEmail(String username);
}