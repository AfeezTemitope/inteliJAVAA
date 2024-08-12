package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

//    Optional<Note> findByTitle(String noteTitle);
}