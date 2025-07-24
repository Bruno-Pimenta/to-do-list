package com.bruno_pimenta_dev.to_do_list.controller;

import com.bruno_pimenta_dev.to_do_list.business.service.TaskService;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("profiles/{user-id}/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<String> insertTask(@Valid @RequestBody TaskRequestDTO dto, @PathVariable("user-id") Long userId){
        Long taskId = service.insertTask(dto, userId);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskId)
                .toUri();
        return ResponseEntity.created(uri).body("Tarefa salva com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasksByUserId(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.getAllTasksByUserId(userId));
    }

    @GetMapping("{task-id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("user-id") Long userId, @PathVariable("task-id") Long taskId) {
        return ResponseEntity.ok(service.getTaskById(userId, taskId));
    }

    @DeleteMapping("{task-id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("user-id") Long userId, @PathVariable("task-id") Long taskId) {
        service.deleteTaskById(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{task-id}")
    public ResponseEntity<String> insertTask(@PathVariable("user-id") Long userId, @PathVariable("task-id") Long taskId, @Valid @RequestBody TaskRequestDTO dto){
        service.updateTask(userId, taskId, dto);
        return ResponseEntity.ok().body("Tarefa alterada com sucesso!");
    }

    @PutMapping("{task-id}/change-task-status")
    public ResponseEntity<String> changeTaskStatus(@PathVariable("user-id") Long userId, @PathVariable("task-id") Long taskId){
        boolean isCompleted = service.changeTaskStatus(userId, taskId);
        return isCompleted? ResponseEntity.ok().body("Tarefa marcada como concluída!"): ResponseEntity.ok().body("Tarefa marcada como não concluída");
    }
}
