package br.com.fiap.apitask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateTaskDto (
        @NotNull
        Long id,
        String title,
        String description,
        Date dueDate,
        Boolean status
){
}
