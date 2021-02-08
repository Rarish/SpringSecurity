package com.example.potic.controller;

import com.example.potic.result.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseMessage login(String username,String password){

        return null;
    }
}
