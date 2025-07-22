package com.bruno_pimenta_dev.to_do_list.controller;

import com.bruno_pimenta_dev.to_do_list.business.TaskService;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskResponseDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profiles/{id}/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public String insertTask(@Valid @RequestBody TaskRequestDTO dto, @PathVariable Integer id){
        if(service.insertTask(dto, id)){
            return "Tarefa salva com sucesso!";
        }return "Falha ao inserir a tarefa";
    }

    @GetMapping
    public List<TaskResponseDTO> tasks(@PathVariable Integer id){
        return service.getAllTasksById(id);
    }
}
