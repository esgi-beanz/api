package fr.esgi.beanz.api.user.dto;

import lombok.Getter;

@Getter
public class CreateUserDTO {
    private String email;

    private String username;

    private String password;
}
