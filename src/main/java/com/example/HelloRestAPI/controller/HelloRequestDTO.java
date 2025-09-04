package com.example.HelloRestAPI.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloRequestDTO {
    @NotBlank(message = "Message cannot be blank")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    @NotBlank(message = "Sender name cannot be blank")
    @Size(max = 200, message = "Sender name cannot exceed 200 characters")
    private String sender;
}
