package com.badAfeez.code.DtoBBY.response;

import com.badAfeez.code.data.models.Contacts;
import lombok.Data;

import java.util.List;
@Data
public class GetContactsResponse {
    private List<Contacts> contacts;
    private String message;

}
