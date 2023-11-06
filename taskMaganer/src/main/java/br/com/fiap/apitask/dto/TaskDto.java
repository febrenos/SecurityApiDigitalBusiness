package br.com.fiap.apitask.dto;

import java.util.Date;

public record TaskDto(
        String title,
        String description,
        Date dueDate
) {
}
