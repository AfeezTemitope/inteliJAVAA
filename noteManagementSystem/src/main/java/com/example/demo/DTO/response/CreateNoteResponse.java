package com.example.demo.DTO.response;

import lombok.Data;

@Data
public class CreateNoteResponse {
    private String message;
    private Long userId;
    private Long noteId;
}
