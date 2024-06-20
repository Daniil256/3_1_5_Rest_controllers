package com.example.demo.conrtoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public ResponseEntity<Object> user(Authentication auth) {
        return ResponseEntity.ok(auth.getPrincipal());
    }
}