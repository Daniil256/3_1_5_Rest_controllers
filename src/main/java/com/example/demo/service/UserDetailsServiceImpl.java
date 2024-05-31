package com.example.demo.service;

import com.example.demo.config.WebSecurityConfig;
import com.example.demo.models.MyUser;
import com.example.demo.models.Role;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final WebSecurityConfig config;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, WebSecurityConfig config) {
        this.userRepository = userRepository;
        this.config = config;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return user;
    }

    public MyUser findUserById(Long userId) {
        Optional<MyUser> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new MyUser());
    }

    public List<MyUser> allUsers() {
        List<MyUser> userList = new java.util.ArrayList<>(List.of());
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public boolean saveUser(MyUser user) {
        user.setId(0L);
        user.setLocked(false);
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void updateUser(MyUser user) {
        if (user.getPassword().isEmpty()) {
            MyUser myUser = userRepository.findById(user.getId()).get();
            user.setPassword(myUser.getPassword());
        } else {
            user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void lockUser(Long id) {
        MyUser user = userRepository.findById(id).get();
        user.setLocked(true);
        userRepository.save(user);
    }

    public void unlockUser(Long id) {
        MyUser user = userRepository.findById(id).get();
        user.setLocked(false);
        userRepository.save(user);
    }


    public void setAdmin(Long id) {
        MyUser user = userRepository.findById(id).get();
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        userRepository.save(user);
    }

    public void setUser(Long id) {
        MyUser user = userRepository.findById(id).get();
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
    }
}