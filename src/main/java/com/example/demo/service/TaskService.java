package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    public List<Task> ListTask(){
        List<Task> taskList=taskRepository.findAll();
        return taskList;
    }
    public  Task addTask(Task task){
        taskRepository.save(task);
        taskRepository.refresh(task);
        return task;
    }
    public  void deleteTask(Task task){
        taskRepository.delete(task);
    }
    public Task updateTask(Task task){
        Task oldTask=taskRepository.find(task.getTask_id());
        oldTask.setTask_name(task.getTask_name());
        return  taskRepository.save(oldTask);
    }
    public Task getTask(String number){
        int number1=Integer.parseInt(number);
        return taskRepository.find(number1);
    }
    public Task find(int id){
        return taskRepository.find(id);
    }
}

