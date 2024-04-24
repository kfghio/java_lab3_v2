package com.example.cat_microservice.exception;

public class NotFoundCatException extends Exception{
    public NotFoundCatException(String message){
        super(message);
    }

    public NotFoundCatException(String message, Throwable cause ){
        super(message, cause);
    }
}
