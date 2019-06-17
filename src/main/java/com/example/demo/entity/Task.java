package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;
    private String  task_name;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime task_start_time;
    private LocalDateTime Task_end_time;
    private String task_sum;
    private String task_state="进行中";
    @OneToMany(mappedBy = "task")
    private List<TaskRecord> taskRecords;
}
