package com.badAfeez.code.dtObby.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateNoteResponse {
    private String message;
    @Id
    private String noteId;
    private String noteTitle;
    private String noteContent;
}
