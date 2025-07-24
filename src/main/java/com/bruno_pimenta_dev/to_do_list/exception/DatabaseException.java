package com.bruno_pimenta_dev.to_do_list.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}