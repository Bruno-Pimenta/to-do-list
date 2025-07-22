package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import jakarta.persistence.Column;

import java.time.Instant;

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Instant taskDate;
    private boolean isCompleted;
}
