package com.example.demo.conrtoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @GetMapping("/")
    public String login() {
        System.out.println("login");
        return "login";
    }
    @GetMapping("/fail-login")
    public String loginFail() {
        System.out.println("fail-login");
        return "login";
    }
}
