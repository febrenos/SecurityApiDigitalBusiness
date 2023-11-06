package br.com.fiap.apitask.dto;

import lombok.Data;

public record AuthRequest (
        String username,
        String password
){}
