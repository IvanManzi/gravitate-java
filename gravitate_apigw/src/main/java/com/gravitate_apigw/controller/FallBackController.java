package com.gravitate_apigw.controller;

import com.gravitate_apigw.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {


    @GetMapping("/")
    public ResponseEntity<Response> fallBackMethod(){
        return ResponseEntity.ok(
                Response.builder()
                        .statusCode(408)
                        .error("Service taking too long to respond. ")
                        .build()
        );
    }
}
