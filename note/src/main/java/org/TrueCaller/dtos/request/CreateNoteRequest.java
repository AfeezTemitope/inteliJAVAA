package org.TrueCaller.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateNoteRequest {
    private String noteTitle;
    private String noteContent;
    private LocalDateTime DateAndTimeCreated;
}
