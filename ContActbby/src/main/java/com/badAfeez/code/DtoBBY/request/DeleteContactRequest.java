package com.badAfeez.code.DtoBBY.request;

import lombok.Data;

@Data
public class DeleteContactRequest {
    private String userId;
    private String phoneNumber;
    private String name;
}
