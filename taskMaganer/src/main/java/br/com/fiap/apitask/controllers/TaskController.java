package br.com.fiap.apitask.controllers;

import br.com.fiap.apitask.dto.TaskDto;
import br.com.fiap.apitask.dto.UpdateTaskDto;
import br.com.fiap.apitask.models.Task;
import br.com.fiap.apitask.repositories.TaskRepository;
import br.com.fiap.apitask.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody TaskDto data, UriComponentsBuilder builder) {
        Task task = new Task(data);
        taskService.registration(task);
        var uri = builder.path("/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> listAll(Pageable pageable) {
        return ResponseEntity.ok(taskService.listAll(pageable));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UpdateTaskDto data) {
        Task task = taskService.update(data);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
