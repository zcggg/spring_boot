package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskRecordService {
    @Autowired
    TaskRecordRepository taskRecordRepository;
    public List<TaskRecord> ListTaskRecord(){
        List<TaskRecord> list= taskRecordRepository.findAll();
        return  list;
    }
    public  TaskRecord addTaskRecord(TaskRecord taskRecord){
        taskRecordRepository.save(taskRecord);
        taskRecordRepository.refresh(taskRecord);
        return taskRecord;
    }
    public void deleteTaskRecord(TaskRecord taskRecord){
        taskRecordRepository.delete(taskRecord);
    }
    public TaskRecord updateTaskRecord(TaskRecord taskRecord){
        TaskRecord oldTaskRecord=taskRecordRepository.find(taskRecord.getTr_id());
        oldTaskRecord.setTask_result(taskRecord.getTask_result()
        );
        return  taskRecordRepository.save(oldTaskRecord);
    }
    public TaskRecord getTaskRecord(String number){
        int number1=Integer.parseInt(number);
        return  taskRecordRepository.find(number1);
    }
    public TaskRecord find(int id){
        return taskRecordRepository.find(id);
    }
    public List<TaskRecord> findTaskRecordByUser_id(String number){
        int number1=Integer.parseInt(number);
        return taskRecordRepository.findByUser(number1);
    }
}
