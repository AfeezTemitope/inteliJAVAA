package com.badAfeez.code.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Data
//@Document
public class Note {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteContent;
    private LocalDateTime noteDateTimeCreatedAt;


}