package com.example.demo.conrtoller;

import com.example.demo.models.MyUser;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("userForm", new MyUser());
        model.addAttribute("rolesList", userService.loadAllRoles());
        return "admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String update(MyUser user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") MyUser userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/registration";
        }
        if (!userService.saveUser(userForm)) {
            System.out.println("Пользователь с таким именем уже существует");
            return "redirect:/registration";
        }
        return "redirect:/admin";
    }
}