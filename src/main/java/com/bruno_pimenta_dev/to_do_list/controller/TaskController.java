package com.bruno_pimenta_dev.to_do_list.controller;

import com.bruno_pimenta_dev.to_do_list.business.TaskService;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskResponseDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("profiles/{user_id}/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public String insertTask(@Valid @RequestBody TaskRequestDTO dto, @PathVariable Integer user_id){
        if(service.insertTask(dto, user_id)){
            return "Tarefa salva com sucesso!";
        }return "Falha ao inserir a tarefa";
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasksById(@PathVariable Integer user_id){
        return service.getAllTasksById(user_id);
    }

    @GetMapping("{task_id}")
    public TaskResponseDTO getTaskById(@PathVariable Integer user_id, @PathVariable Integer task_id) { return service.getTaskById(user_id, task_id); }

    @DeleteMapping("{task_id}")
    public String deleteTaskById(@PathVariable Integer user_id, @PathVariable Integer task_id) {
        return service.deleteTaskById(user_id, task_id) ? "Tarefa apagada com sucesso!" : "Falha ao apagar tarefa";
    }
}
