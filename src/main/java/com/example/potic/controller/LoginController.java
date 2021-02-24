package com.example.potic.controller;

import com.example.potic.result.ResponseEntity;
import com.example.potic.result.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/error1")
    public ResponseEntity error(){
        return ResponseMessage.fail("登录失败");
    }
}
