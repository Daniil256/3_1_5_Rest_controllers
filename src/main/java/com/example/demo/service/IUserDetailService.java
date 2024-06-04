package com.example.demo.service;

import com.example.demo.models.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

interface IUserDetailService extends UserDetailsService {

    MyUser findUserById(Long userId);

    List<MyUser> allUsers();

    boolean saveUser(MyUser user);

    void updateUser(MyUser user);

    void deleteUser(Long userId);

    void lockUser(Long id);

    void unlockUser(Long id);

}
