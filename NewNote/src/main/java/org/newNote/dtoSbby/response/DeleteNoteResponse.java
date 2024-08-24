package org.newNote.dtoSbby.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteNoteResponse {
    private String message;
    private String noteTitle;
}
