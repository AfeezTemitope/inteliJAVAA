package org.TrueCaller.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document
public class Users {
    @Id
    private String Id;
    private String Name;
    private String Email;
    private String Password;
    private List<Note> noteList;

}
