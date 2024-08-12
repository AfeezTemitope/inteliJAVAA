package org.newNote.dtoSbby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
public class CreateNoteRequest {
    private String noteTitle;
    private String noteContent;
    private LocalDateTime DateAndTimeCreated;
    @Id
    private String noteId;
}
