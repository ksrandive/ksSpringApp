package com.ks.app.error;

public class UserTokenException extends  RuntimeException{

    public UserTokenException(String message){
        super(message);
    }
}
