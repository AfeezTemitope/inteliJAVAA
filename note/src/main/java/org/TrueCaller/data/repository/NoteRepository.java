package org.TrueCaller.data.repository;

import org.TrueCaller.data.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NoteRepository extends MongoRepository<Note, String> {
}
