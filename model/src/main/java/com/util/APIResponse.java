package com.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private final int statusCode;

    private final HttpStatus status;

    private final Map<String, Object> headers;

    private final Map<String,Object> data;

    private  final String message;

    public static ResponseEntity<APIResponse> resourceNotFound(){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .status(HttpStatus.NOT_FOUND)
                        .message("Operation failed.")
                        .build()
        );
    }

    public static ResponseEntity resultSuccess(HttpStatus statusCode, Map<String,Object> data){
        Map<String,Object> response = new HashMap<>();
        response.put(Constants.RETURN_DATA,data);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(statusCode.value())
                        .status(statusCode)
                        .data(response)
                        .message("Operation success.")
                        .build()
        );
    }

    public static ResponseEntity resultSuccess(HttpStatus statusCode, String developerMessage){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(statusCode.value())
                        .status(statusCode)
                        .message(developerMessage)
                        .build()
        );
    }

    public static ResponseEntity resultSuccess(String developerMessage){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED)
                        .message(developerMessage)
                        .build()
        );
    }

    public static ResponseEntity resultSuccess(Map<String,Object> data){
        Map<String,Object> response = new HashMap<>();
        response.put(Constants.RETURN_DATA,data);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED)
                        .data(response)
                        .message("Operation success.")
                        .build()
        );
    }

    public static ResponseEntity resultSuccess(){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED)
                        .message("Operation success.")
                        .build()
        );
    }

    public static ResponseEntity resultFail(HttpStatus statusCode){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(statusCode.value())
                        .status(statusCode)
                        .message("Operation failed.")
                        .build()
        );
    }

    public static ResponseEntity resultFail(HttpStatus statusCode,String developerMessage){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(statusCode.value())
                        .status(statusCode)
                        .message(developerMessage)
                        .build()
        );
    }

    public static ResponseEntity resultFail(){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Operation failed.")
                        .build()
        );
    }

    public static ResponseEntity resultFail(String developerMessage){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(developerMessage)
                        .build()
        );
    }


}

