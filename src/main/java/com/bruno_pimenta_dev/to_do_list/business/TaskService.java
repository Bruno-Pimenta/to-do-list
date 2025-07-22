package com.bruno_pimenta_dev.to_do_list.business;

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

    public List<TaskResponseDTO> getAllTasksById(Integer id){
        List<Task> tasks = taskRepository.getAllTasksById(id);
        List<TaskResponseDTO> dtos = new ArrayList<>();

        tasks.forEach(task ->{
            TaskResponseDTO dto = TaskResponseDTO.tasktoDTO(task);
            dtos.add(dto);
        });
        return dtos;
    }

}
