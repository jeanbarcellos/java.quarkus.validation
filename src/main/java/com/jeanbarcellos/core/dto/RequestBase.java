package com.jeanbarcellos.core.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class RequestBase {

    @JsonIgnore
    protected LocalDateTime timestamp = LocalDateTime.now();

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
