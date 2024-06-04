package com.example.demo.conrtoller;

import com.example.demo.models.MyUser;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        MyUser user = userService.findUserById(id);
        user.setPassword("");
        user.setRoles(List.of());
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.loadAllRoles());
        return "editor";
    }

    @PostMapping("/edit")
    public String update(MyUser user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/lock")
    public String lockUser(@RequestParam("id") long id) {
        userService.lockUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/unlock")
    public String unlockUser(@RequestParam("id") long id) {
        userService.unlockUser(id);
        return "redirect:/admin";
    }
}