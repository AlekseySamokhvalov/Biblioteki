package com.example.varietyOfImplementations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateException extends HttpStatusCodeException {

    public ValidateException(String message) { super(HttpStatus.BAD_REQUEST,message);}
}
