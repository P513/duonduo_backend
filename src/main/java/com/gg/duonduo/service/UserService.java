package com.gg.duonduo.service;

import com.gg.duonduo.domain.UserDto;
import com.gg.duonduo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;

    public List<UserDto> userList() {
        System.out.println(userMapper.userList());
        System.out.println("유저리스트 출력 시도..");
        return userMapper.userList();
    }


    public UserDto fetchUserByUserID(long id) {
        System.out.println(userMapper.fetchUserByUserID(id));
        System.out.println("유저 ID로 조회 시도..");
        return userMapper.fetchUserByUserID(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.fetchUserByEmail(username);
    }

}
