package com.LoanProDevTeam.LoanPro.Exceptions;

import lombok.Builder;

@Builder
public class BadApiRequestException extends RuntimeException {
    public BadApiRequestException(){
        super("Resource Not Found");
    }

    public BadApiRequestException(String message){
        super(message);
    }
}
