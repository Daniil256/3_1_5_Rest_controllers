package com.example.demo.service;

import com.example.demo.config.WebSecurityConfig;
import com.example.demo.models.MyUser;
import com.example.demo.models.Role;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
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
    private final EntityManager em;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, WebSecurityConfig config, EntityManager em) {
        this.userRepository = userRepository;
        this.config = config;
        this.em = em;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
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
        MyUser userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setId(0L);
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void blockUser(Long userId) {
        MyUser user = userRepository.findById(userId).get();
        user.setNoNLocked(false);
        userRepository.save(user);
    }

    public void unlockUser(Long userId) {
        MyUser user = userRepository.findById(userId).get();
        user.setNoNLocked(true);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    public List<MyUser> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM MyUser u WHERE u.id > :paramId", MyUser.class)
                .setParameter("paramId", idMin).getResultList();
    }
}