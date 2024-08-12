package com.badAfeez.code.dtObby.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class EditNoteResponse {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteContent;
    private String message;
    private boolean success;
}
