package com.example.HelloRestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloRepository helloRepository;


    @Override
    public HelloResponseDTO createHelloMessage(HelloRequestDTO helloRequestDTO) {
        Hello hello = new Hello();
        hello.setMessage(helloRequestDTO.getMessage());
        hello.setSender(helloRequestDTO.getSender());
        hello.setCreatedAt(LocalDateTime.now());
        Hello savedHello = helloRepository.save(hello);
        return new HelloResponseDTO(savedHello);
    }

    @Override
    public List<HelloResponseDTO> getAllHelloMessage() {
        return helloRepository.findLatestMessages()//findAll()
                .stream()
                .map(HelloResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HelloResponseDTO> getHelloMessageById(Long id) {
        return helloRepository.findById(id)
                .map(HelloResponseDTO::new);
    }

    @Override
    public List<HelloResponseDTO> getHelloMessagesBySender(String sender) {
        return helloRepository.findBySenderOrderByCreatedAtDesc(sender)
                .stream()
                .map(HelloResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<HelloResponseDTO> searchHelloMessages(String searchText) {
        return helloRepository.findByMessageContainingIgnoreCase(searchText)
                .stream()
                .map(HelloResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHelloMessage(Long id) {
        if(helloRepository.existsById(id)) {
            helloRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Hello message not found with id: " + id);
        }
    }

    @Override
    public HelloResponseDTO updateHelloMessage(Long id, HelloRequestDTO helloRequestDTO) {
        Optional<Hello> hello = helloRepository.findById(id);
        if(hello.isPresent()) {
            Hello hello1 = hello.get();
            hello1.setMessage(helloRequestDTO.getMessage());
            hello1.setSender(helloRequestDTO.getSender());

            Hello updated = helloRepository.save(hello1);
            return new HelloResponseDTO(updated);
        }
        else {
            throw new RuntimeException("Hello message not found with id: "+ id);
        }
    }
}
