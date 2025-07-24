package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String date;
    private String time;
    private boolean isCompleted;

    public static TaskResponseDTO taskToDTO(Task task){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(task.getTaskDate(), ZoneId.systemDefault());
        String date = localDateTime.toLocalDate().toString();
        String time = localDateTime.toLocalTime().toString();

        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(),
                date, time, task.isCompleted());
    }
}
