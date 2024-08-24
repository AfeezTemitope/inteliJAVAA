package com.badAfeez.code.dtObby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateNoteRequest {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteContent;
}
