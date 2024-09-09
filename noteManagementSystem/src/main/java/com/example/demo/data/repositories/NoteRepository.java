package com.example.demo.data.repositories;

import com.example.demo.data.models.Note;
import com.example.demo.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByTitle(String normalizedTitle);
    List<Note> findByUser(User user);
}
