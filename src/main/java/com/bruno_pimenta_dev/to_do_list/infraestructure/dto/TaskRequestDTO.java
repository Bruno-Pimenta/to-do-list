package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotBlank(message = "A data é obrigatória (formato: yyyy-MM-dd)")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato yyyy-MM-dd")
    private String date; // Exemplo: "2025-07-25"

    @NotBlank(message = "A hora é obrigatória (formato: HH:mm)")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "A hora deve estar no formato HH:mm")
    private String time; // Exemplo: "14:30"

    public static Task dtoToTask(TaskRequestDTO dto, Profile profile){
        Instant taskDate = toInstant(dto);
        Task task = new Task(dto.getTitle(), dto.getDescription(), taskDate, profile);
        return task;
    }

    private static Instant toInstant(TaskRequestDTO dto) {
        LocalDateTime localDateTime = LocalDateTime.parse(
                dto.getDate() + "T" + dto.getTime()
        );
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
