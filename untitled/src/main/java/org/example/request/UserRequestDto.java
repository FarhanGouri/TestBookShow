package org.example.request;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Roles;

@Getter
@Setter
public class UserRequestDto {

    private String name;
    private String email;
    private Roles role;
    private String password;

}
