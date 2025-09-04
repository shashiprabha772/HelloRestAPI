package com.example.HelloRestAPI.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloResponseDTO {
    private Long id;
    private String message;
    private String sender;
    private LocalDateTime createdAt;

    public HelloResponseDTO(Hello hello) {
        this.id = hello.getId();
        this.message = hello.getMessage();
        this.sender = hello.getSender();
        this.createdAt = hello.getCreatedAt();
    }
}
