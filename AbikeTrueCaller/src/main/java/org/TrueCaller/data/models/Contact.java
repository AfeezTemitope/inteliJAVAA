package org.TrueCaller.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Contact {
    private String id;
    private String name;
    private String phoneNumber;


}
