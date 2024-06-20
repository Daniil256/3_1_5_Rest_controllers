package com.example.demo.conrtoller;

import com.example.demo.models.MyUser;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserDetailsServiceImpl userService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> showAllUsers(Authentication auth) {
        return ResponseEntity.ok(List.of(userService.allUsers(), auth.getPrincipal()));
    }

    @GetMapping("/delete")
    public ResponseEntity.BodyBuilder deleteUser(@RequestParam Long id) {
        System.out.println("DELETE");
        userService.deleteUser(id);
        return ResponseEntity.ok();
    }

    @PostMapping("/edit")
    public ResponseEntity.BodyBuilder update(MyUser user) {
        System.out.println("EDIT");
        userService.updateUser(user);
        return ResponseEntity.ok();
    }

    @PostMapping("/registration")
    public ResponseEntity.BodyBuilder addUser(MyUser userForm, BindingResult bindingResult) {
        System.out.println("REG");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return ResponseEntity.badRequest();
        }
        if (!userService.saveUser(userForm)) {
            System.out.println("Пользователь с таким именем уже существует");
            return ResponseEntity.badRequest();
        }
        return ResponseEntity.ok();
    }
}