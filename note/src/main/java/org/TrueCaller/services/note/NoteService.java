package org.TrueCaller.services.note;

import org.TrueCaller.dtos.request.CreateNoteRequest;
import org.TrueCaller.dtos.response.CreateNoteResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);
    Long countNumberOfNote();
}
