package Service;

import org.TrueCaller.dtos.request.CreateNoteRequest;
import org.TrueCaller.dtos.response.CreateNoteResponse;
import org.TrueCaller.services.note.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NoteServiceTest {
    @Autowired
    private  NoteService noteService;

    @Test
    public void testThatNoteCanBeCreated(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteTitle("chibuzor is wicked");
        createNoteRequest.setNoteContent("i wish i can strangle him");
        createNoteRequest.setDateAndTimeCreated(LocalDateTime.now());
        CreateNoteResponse response = noteService.createNote(createNoteRequest);
        response.setMessage("Note created");
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("Note created succesfully");
        assertEquals(1, noteService.countNumberOfNote());
    }
}
