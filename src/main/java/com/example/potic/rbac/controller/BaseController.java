package com.example.potic.rbac.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.potic.result.ResponseEntity;
import com.example.potic.result.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class BaseController<S extends ServiceImpl,T extends Object> {
    @Autowired
    private S service;

    @PostMapping("/addOrUpdate")
    public ResponseEntity addOrUpdate(List<T> data){
        service.saveOrUpdateBatch(data);
        return ResponseMessage.success();
    }

    @PostMapping("/delete")
    public ResponseEntity deleteByIds(List<Integer> ids){
        service.removeByIds(ids);
        return ResponseMessage.success();
    }

    @PostMapping("/list")
    public ResponseEntity listById(){
        service.list();
        return ResponseMessage.success();
    }
}
