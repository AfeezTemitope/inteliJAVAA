package com.badAfeez.code.dtObby.response;

import lombok.Data;

@Data
public class GetNoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteContent;
}
