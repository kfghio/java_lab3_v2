package com.example.owner_microservice.exception;

public class NotFoundOwnerException extends Exception {
    public NotFoundOwnerException(String message){
        super(message);
    }
}
