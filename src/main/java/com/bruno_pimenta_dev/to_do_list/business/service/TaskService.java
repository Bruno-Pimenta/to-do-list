package com.bruno_pimenta_dev.to_do_list.business.service;

import com.bruno_pimenta_dev.to_do_list.exception.DatabaseException;
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

    public Long insertTask(TaskRequestDTO dto, Long id){
        Optional<Profile> optional = profileRepository.findById(id);
        if(optional.isEmpty()){
            throw new DatabaseException("Falha ao cadastrar a tarefa");
        }Task task = TaskRequestDTO.dtoToTask(dto, optional.get());
        Task taskSaved = taskRepository.save(task);
        return taskSaved.getId();
    }

    public List<TaskResponseDTO> getAllTasksByUserId(Long userId){
        List<Task> tasks = taskRepository.getAllTasksByProfileId(userId);
        List<TaskResponseDTO> dtos = new ArrayList<>();

        tasks.forEach(task ->{
            TaskResponseDTO dto = TaskResponseDTO.taskToDTO(task);
            dtos.add(dto);
        });
        return dtos;
    }

    public TaskResponseDTO getTaskById (Long userId, Long taskId) {
        Optional<Task> optional = Optional.ofNullable(this.taskRepository.getTaskById(userId, taskId));
        if (optional.isPresent()) {
            return TaskResponseDTO.taskToDTO(optional.get());
        }
        throw new ResourceNotFoundException("Task não encontrada");
    }

    public Long updateTask(Long userId, Long taskId, TaskRequestDTO dto){
        Optional<Task> optionalTask = Optional.ofNullable(this.taskRepository.getTaskById(userId, taskId));
        Optional<Profile> optionalProfile = this.profileRepository.findById(userId);
        if(optionalProfile.isPresent() && optionalTask.isPresent()){
            Task taskUpdated = TaskRequestDTO.dtoToTask(dto, optionalProfile.get());
            taskUpdated.setId(optionalTask.get().getId());
            this.taskRepository.save(taskUpdated);
            return taskUpdated.getId();
        }else if(optionalTask.isEmpty()){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }
        throw new DatabaseException("Falha ao editar a tarefa");
    }

    public void deleteTaskById (Long userId, Long taskId) {
        if(taskRepository.existsById(taskId) && taskRepository.existsById(userId)){
            taskRepository.deleteById(taskId);
            return;
        }
        throw new ResourceNotFoundException("Task não encontrada");
    }

    public boolean changeTaskStatus(Long userId, Long taskId){
        Optional<Task> optionalTask = Optional.ofNullable(this.taskRepository.getTaskById(userId, taskId));
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            taskRepository.saveAndFlush(task);
            return task.isCompleted();
        }throw new DatabaseException("Falha ao editar a tarefa");
    }
}
