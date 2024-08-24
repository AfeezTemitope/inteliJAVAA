package com.badAfeez.code.dtoBby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class CreateArtistRequest {
    private String name;
    private String age;
    private String gender;
    private String phoneNumber;
    private String userName;

}
