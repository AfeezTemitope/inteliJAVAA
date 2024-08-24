package com.badAfeez.code.dtObby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
public class CreateNoteRequest {
    private String noteTitle;
    private String noteContent;
    @Id
    private String noteId;
    private LocalDateTime DateAndTimeCreated;
}
