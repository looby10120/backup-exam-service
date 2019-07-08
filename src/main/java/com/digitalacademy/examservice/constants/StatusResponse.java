package com.digitalacademy.examservice.constants;

import lombok.Getter;

@Getter
public enum StatusResponse {

    // You should be able to create an enum object of a status response here.
    GET_REQURST_SUCCESS(1000, "request success"), // request 200
    GET_RESPONSE_SUCCESS(1000, "success"), // response 200
    GET_CREATED_SUCCESS(1000, "created success"), // create 201
    GET_BAD_REQUEST(1499, "bad request"), // bad 400
    GET_WRONG_PASSWORD(1599, "enter wrong password"), // 401
    GET_NOT_FOUND_RESOURCE_IN_DATABASE(1699, "not found resource in database"), //  not found 404
    GET_REQUEST_TIMEOUT(1799, "request timeout"), // 408
    GET_TECHNICAL_ERROR(1899, "technical error"), // xxx เช่น method error
    GET_REQUEST_WRONG_URL_PATH(1999, "request wrong URL path"), // not found 404
    GET_DEATH_SERVER(9900, "death server"); //   ออดตาย 500


    private final int code;
    private final String message;

    StatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
