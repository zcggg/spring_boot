package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Tr_id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User user;
    private String task_reply;
    private LocalDateTime comp_time;
    private String task_result="未完成";
}
