package com.bruno_pimenta_dev.to_do_list.infraestructure.repository;

import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    final String SELECT = "SELECT * from tb_tasks ";

    @Query(nativeQuery = true, value = SELECT + "WHERE profile_id = :id")
    List<Task> getAllTasksById(Integer id);

    @Query(nativeQuery = true, value = SELECT + "WHERE profile_id = :user_id " + "AND id = :task_id")
    Task getTaskById(Integer user_id, Integer task_id);
}
