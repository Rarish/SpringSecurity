package com.example.potic.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private int code;
    private String message;
    private T data;

    public ResponseEntity(T data){
        this.data = data;
    }
}
