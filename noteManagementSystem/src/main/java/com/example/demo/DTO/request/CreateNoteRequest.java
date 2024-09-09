package com.example.demo.DTO.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNoteRequest {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long userId;
}
