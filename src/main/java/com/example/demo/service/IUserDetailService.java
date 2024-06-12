package com.example.demo.service;

import com.example.demo.models.MyUser;
import com.example.demo.models.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

interface IUserDetailService extends UserDetailsService {
    List<Role> loadAllRoles();

    List<MyUser> allUsers();

    boolean saveUser(MyUser user);

    void updateUser(MyUser user);

    void deleteUser(Long userId);

}
