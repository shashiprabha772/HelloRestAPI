package com.example.HelloRestAPI.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {
    // Fixed: CreateAt -> CreatedAt to match entity field name
    List<Hello> findBySenderOrderByCreatedAtDesc(String sender);
    
    // Added proper query for latest messages
    @Query("SELECT h FROM Hello h ORDER BY h.createdAt DESC")
    List<Hello> findLatestMessages();
    
    List<Hello> findByMessageContainingIgnoreCase(String text);
}
