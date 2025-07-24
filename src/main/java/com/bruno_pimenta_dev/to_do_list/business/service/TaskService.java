package com.bruno_pimenta_dev.to_do_list.business.service;

import com.bruno_pimenta_dev.to_do_list.exception.ResourceNotFoundException;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.TaskResponseDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import com.bruno_pimenta_dev.to_do_list.infraestructure.repository.ProfileRepository;
import com.bruno_pimenta_dev.to_do_list.infraestructure.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public boolean insertTask(TaskRequestDTO dto, Integer id){
        Profile profile = profileRepository.findById(id).orElse(null);
        if(profile==null){
            return false;
        }Task task = TaskRequestDTO.dtoToTask(dto, profile);
        Task taskSaved = taskRepository.save(task);
        if(taskSaved!=null){
            return true;
        }return false;
    }

    public List<TaskResponseDTO> getAllTasksById(Integer user_id){
        List<Task> tasks = taskRepository.getAllTasksById(user_id);
        List<TaskResponseDTO> dtos = new ArrayList<>();

        tasks.forEach(task ->{
            TaskResponseDTO dto = TaskResponseDTO.taskToDTO(task);
            dtos.add(dto);
        });
        return dtos;
    }

    public TaskResponseDTO getTaskById (Integer user_id, Integer task_id) {
        Optional<Task> optional = Optional.ofNullable(this.taskRepository.getTaskById(user_id, task_id));
        if (optional.isPresent()) {
            return TaskResponseDTO.taskToDTO(optional.get());
        }
        throw new ResourceNotFoundException("Task não encontrada");
    }

    public boolean deleteTaskById (Integer user_id, Integer task_id) {
        taskRepository.deleteById(task_id);
        Task task = taskRepository.getTaskById(user_id, task_id);
        return task == null;
    }
}
