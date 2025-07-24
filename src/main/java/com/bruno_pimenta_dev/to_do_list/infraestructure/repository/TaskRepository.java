package com.bruno_pimenta_dev.to_do_list.infraestructure.repository;

import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    final String SELECTALLFROMTASKS = "SELECT * from tb_tasks ";

    @Query(nativeQuery = true, value = SELECTALLFROMTASKS + "WHERE profile_id = :profileId")
    List<Task> getAllTasksByProfileId(Long profileId);

    @Query(nativeQuery = true, value = SELECTALLFROMTASKS + "WHERE profile_id = :userId " + "AND id = :taskId")
    Task getTaskById(Long userId, Long taskId);
}
