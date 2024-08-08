package com.axis.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        System.out.println("User Not found");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
