package fr.esgi.beanz.api.auth.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class LoginDTO {

    @NotNull(message = "Please provide a username.")
    private String username;

    @NotNull(message = "please provide a password")
    private String password;
}
