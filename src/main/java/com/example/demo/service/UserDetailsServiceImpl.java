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

import java.util.List;
import java.util.Optional;

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

    public List<Role> loadAllRoles() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return user;
    }

    @Override
    public MyUser findUserById(Long userId) {
        Optional<MyUser> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new MyUser());
    }

    @Override
    public List<MyUser> allUsers() {
        List<MyUser> userList = new java.util.ArrayList<>(List.of());
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public boolean saveUser(MyUser user) {
        user.setId(0L);
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void updateUser(MyUser user) {
        if (user.getPassword().isEmpty()) {
            MyUser myUser = userRepository.findById(user.getId()).get();
            user.setPassword(myUser.getPassword());
        } else {
            user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void lockUser(Long id) {

    }

    @Override
    public void unlockUser(Long id) {

    }
}