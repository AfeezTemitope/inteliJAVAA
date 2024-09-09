package com.badAfeez.artSalesMgt.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArtist {
    private Long id;
    private String name;
    private String age;
    private String gender;
    private String username;
}
