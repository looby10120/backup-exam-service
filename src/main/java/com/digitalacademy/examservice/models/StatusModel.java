package com.digitalacademy.examservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusModel {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public StatusModel() {
    }

    public StatusModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

}