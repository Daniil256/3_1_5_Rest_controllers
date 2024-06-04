package com.example.demo.conrtoller;

import com.example.demo.models.MyUser;
import com.example.demo.models.Role;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class RegistrationController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public RegistrationController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new MyUser());
        List<Role> rolesList = userService.loadAllRoles();
        model.addAttribute("rolesList", rolesList);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") MyUser userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/registration";
        }
        if (!userService.saveUser(userForm)) {
            System.out.println("Пользователь с таким именем уже существует");
            return "registration";
        }

        return "index";
    }
}