package br.com.fiap.apitask.dto;

import br.com.fiap.apitask.models.UserRole;

public record UserInfoDto(
        String username,
        String password,
        UserRole roles
) {
}
