package com.axis.exceptions;


public class UserRoleNotFoundException extends Exception{
    public UserRoleNotFoundException(){
        System.out.println("User role Not found");
    }
    public UserRoleNotFoundException(String message) {
        super(message);
    }
}