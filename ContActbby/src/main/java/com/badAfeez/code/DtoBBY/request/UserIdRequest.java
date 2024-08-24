package com.badAfeez.code.DtoBBY.request;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class UserIdRequest {
    @Id
    private String id;
}
