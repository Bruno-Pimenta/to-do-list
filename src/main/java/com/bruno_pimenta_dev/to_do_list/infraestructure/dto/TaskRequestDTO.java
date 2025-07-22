package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
}
