package br.com.fiap.apitask.models;

import jakarta.persistence.*;
import lombok.Data;
import br.com.fiap.apitask.dto.TaskDto;



import java.util.Date;

@Entity
@Data
public class Task {
    public Task(TaskDto data) {
        this.title = data.title();
        this.description = data.description();
        this.dueDate = data.dueDate();
        this.status = false;
        this.isActive = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Boolean status;
    private Date dueDate;
    private Boolean isActive;

    public Task() {}
}
