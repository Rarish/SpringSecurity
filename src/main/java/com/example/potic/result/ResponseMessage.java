package com.example.potic.result;

public class ResponseMessage {
    public static ResponseEntity success(){
        return new ResponseEntity(1,"success",null);
    }

    public static ResponseEntity success(String message){
        return new ResponseEntity(1,message,null);
    }

    public static ResponseEntity success(String message, Object data){
        return new ResponseEntity(1,message,data);
    }

    public static ResponseEntity success(Object data){
        return new ResponseEntity(data);
    }

    public static ResponseEntity fail(){
        return new ResponseEntity(0,"fail",null);
    }

    public static ResponseEntity fail(String message){
        return new ResponseEntity(0,message,null);
    }

    public static ResponseEntity fail(String message, Object data){
        return new ResponseEntity(0,message,data);
    }
}
