package org.newNote.dtoSbby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteNoteRequest {
    private String noteTitle;

}
