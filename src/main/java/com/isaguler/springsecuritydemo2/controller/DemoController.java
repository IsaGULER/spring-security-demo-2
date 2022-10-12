package com.isaguler.springsecuritydemo2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    @GetMapping("/v1/admin")
    public ResponseEntity<Object> adminApi() {
        return ResponseEntity.ok().body("admin");
    }

    @GetMapping("/v1/user")
    public ResponseEntity<Object> userApi() {
        return ResponseEntity.ok().body("user");
    }

    @GetMapping("/v1/public")
    public ResponseEntity<Object> publicApi() {
        return ResponseEntity.ok().body("public");
    }
}
