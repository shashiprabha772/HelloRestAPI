package com.example.HelloRestAPI.controller;

import java.util.List;
import java.util.Optional;

public interface HelloService {
    HelloResponseDTO createHelloMessage(HelloRequestDTO helloRequestDTO);
    List<HelloResponseDTO> getAllHelloMessage();
    Optional<HelloResponseDTO> getHelloMessageById(Long id);
    List<HelloResponseDTO> getHelloMessagesBySender(String sender);
    List<HelloResponseDTO> searchHelloMessages(String searchText);
    void deleteHelloMessage(Long id);
    HelloResponseDTO updateHelloMessage(Long id, HelloRequestDTO helloRequestDTO);

}
