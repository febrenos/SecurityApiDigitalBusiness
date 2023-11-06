package br.com.fiap.apitask.dto;

import br.com.fiap.apitask.models.UserRole;
import br.com.fiap.apitask.models.UserData;


public record UserCreatedDto (Long id, String username, UserRole roles){

    public UserCreatedDto (UserData user) {
        this (user.getId(), user.getUsername(), user.getRole());
    }
}
