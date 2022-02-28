package com.company.blog.enums;

public enum ErrorCase {

    POST_NOT_FOUND(String.format("%"));
    private String message;

    ErrorCase(String message) {
        this.message = message;
    }
}
