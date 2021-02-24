package com.example.potic.controller;

import com.example.potic.result.ResponseEntity;
import com.example.potic.result.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {
    @PostMapping("/root")
    public ResponseEntity testRoot(){
        return ResponseMessage.success();
    }

    @PostMapping("/admin")
    public ResponseEntity testAdmin(){
        return ResponseMessage.success();
    }

    @PostMapping("/user")
    public ResponseEntity testUser(){
        return ResponseMessage.success();
    }

    @PostMapping("/visitor")
    public ResponseEntity testVisitor(){
        return ResponseMessage.success();
    }
}
