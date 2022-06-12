package com.gg.duonduo.repository;

import java.util.List;

import com.gg.duonduo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    List<User> userList();
    User fetchUserByID(int id);
    void updateUser(User user);
    void insertUser(User user);
    void deleteUser(int id);
}