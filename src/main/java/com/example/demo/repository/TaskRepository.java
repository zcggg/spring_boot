package com.example.demo.repository;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CustomizedRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.task_id=:id")
    Task find(@Param("id") int number);
}
