package com.bruno_pimenta_dev.to_do_list.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "task_date", nullable = false)
    private Instant taskDate;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}
