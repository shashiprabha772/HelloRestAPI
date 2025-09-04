package com.example.HelloRestAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping
    public ResponseEntity<String> simpleHello() {
        return ResponseEntity.ok("Hello from Hello Rest API");
    }

    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greetUser(@PathVariable String name) {
        return ResponseEntity.ok("Hello " + name + "! Welcome to Hello Rest API!");
    }

    @GetMapping("/status")
    public ResponseEntity<Object> getAPIStatus() {
        return  ResponseEntity.ok(new Object() {
            public String status = "UP";
            public String service = "Hello API";
            public String version = "1.0.0";
            public String timestamp = LocalDateTime.now().toString();
        });
    }

    @PostMapping("/messages")
    public ResponseEntity<HelloResponseDTO> createHelloMessage(@Valid @RequestBody HelloRequestDTO helloRequestDTO) {
        try {
            HelloResponseDTO responseDTO = helloService.createHelloMessage(helloRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body((responseDTO));
        }
        catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/message")
    public ResponseEntity<List<HelloResponseDTO>> getAllMessages() {
        try {
            List<HelloResponseDTO> messages = helloService.getAllHelloMessage();
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<HelloResponseDTO> getMessageById(Long id) {
        try {
            Optional<HelloResponseDTO> message = helloService.getHelloMessageById(id);
            return  message.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/messages/sender/{sender}")
    public ResponseEntity<List<HelloResponseDTO>> getMessagesBySender(@PathVariable String sender) {
        try {
            List<HelloResponseDTO> messages = helloService.getHelloMessagesBySender(sender);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<HelloResponseDTO> updateMessagesById(@PathVariable Long id, @Valid @RequestBody HelloRequestDTO helloRequestDTO) {
        try {
            HelloResponseDTO helloResponseDTO = helloService.updateHelloMessage(id, helloRequestDTO);
            return ResponseEntity.ok(helloResponseDTO);
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<String> deleteHelloMessage(@PathVariable Long id) {
        try {
            helloService.deleteHelloMessage(id);
            return ResponseEntity.ok("Hello message deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
