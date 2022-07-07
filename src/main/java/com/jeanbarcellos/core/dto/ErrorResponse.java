package com.jeanbarcellos.core.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private Integer status = 500;

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
