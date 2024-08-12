package org.newNote.dtoSbby.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateNoteResponse {
    private String message;
    @Id
    private String noteId;
}
