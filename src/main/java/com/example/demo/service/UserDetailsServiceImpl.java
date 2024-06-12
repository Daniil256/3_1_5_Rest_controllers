package com.example.demo.service;

import com.example.demo.config.WebSecurityConfig;
import com.example.demo.models.MyUser;
import com.example.demo.models.Role;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements IUserDetailService {
    private final UserRepository userRepository;
    private final WebSecurityConfig config;
    private final EntityManager em;

    @Autowired
    public UserDetailsServiceImpl(EntityManager em, UserRepository userRepository, WebSecurityConfig config) {
        this.userRepository = userRepository;
        this.config = config;
        this.em = em;
    }

    @Transactional
    @Override
    public List<Role> loadAllRoles() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return user;
    }

    @Transactional
    @Override
    public List<MyUser> allUsers() {
        List<MyUser> userList = new java.util.ArrayList<>(List.of());
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Transactional
    @Override
    public boolean saveUser(MyUser user) {
        user.setId(0L);
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public void updateUser(MyUser user) {
        if (user.getPassword().isEmpty()) {
            MyUser myUser = userRepository.findById(user.getId()).get();
            user.setPassword(myUser.getPassword());
        } else {
            user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        }
        if (user.getRoles() == null) {
            MyUser myUser = userRepository.findById(user.getId()).get();
            user.setRoles(myUser.getRoles());
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}