package com.gg.duonduo.service;

import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDto> userList() {
        System.out.println(userMapper.userList());
        System.out.println("유저리스트 출력 시도..");
        return userMapper.userList();
    }


    public UserDto fetchUserByID(long id) {
        System.out.println(userMapper.fetchUserByID(id));
        System.out.println("유저 ID로 조회 시도..");
        return userMapper.fetchUserByID(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.fetchUserByEmail(username);
    }

}
