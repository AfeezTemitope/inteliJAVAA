package org.newNote.data.repository;

import org.newNote.data.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {

    Optional<Note> findByTitle(String noteTitle);
}
