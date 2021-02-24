package com.example.potic.frame.exception;

import com.example.potic.result.ResponseEntity;
import com.example.potic.result.ResponseMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handle(Throwable e){
        if(e instanceof CustomException){
            return ResponseMessage.fail("用户不存在或密码错误");
        }
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        return ResponseMessage.fail(e.getMessage());
    }
}
