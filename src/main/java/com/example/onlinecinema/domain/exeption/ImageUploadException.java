package com.example.onlinecinema.domain.exeption;

public class ImageUploadException extends RuntimeException{
    public ImageUploadException(String message){
        super(message);
    }
}
