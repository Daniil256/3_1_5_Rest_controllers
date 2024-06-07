package com.example.demo.models;

import java.util.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class MyUser implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getRolesName() {
        if (roles.toString().matches(".*ROLE_ADMIN.*")) {
            if (roles.toString().matches(".*ROLE_USER.*")) {
                return "ADMIN USER";
            } else return "ADMIN";
        } else return "USER";
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
