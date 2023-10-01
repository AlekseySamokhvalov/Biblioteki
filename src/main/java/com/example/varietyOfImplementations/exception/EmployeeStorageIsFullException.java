package com.example.varietyOfImplementations.exception;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String s) {
        super(s);
    }
}
