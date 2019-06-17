package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    InvigilationService invigilationService;
    @Autowired
    DistributionService distributionService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRecordService taskRecordService;
    @GetMapping("/admin")
    public Map test03(@RequestAttribute String user_id){
        User user = userService.getUser(user_id);
        Map map=Map.of("user",user,"message","查找成功");
        return map;
    }
    @PostMapping("/UserPage/Admin/updateUser")
    public Map updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Map.of("message","更新用户成功");
    }
    @PostMapping("/UserPage/Admin/deleteUser")
    public Map deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return Map.of("message","删除用户成功");
    }
    @PostMapping("/UserPage/Admin/addUser")
    public Map addUser(@RequestBody User user){
       String message;
        if(user.getUser_name()!="") {
        userService.addUser(user);
        message="添加用户成功";
        }
        else {
            message="用户名不能为空";
        }
        return Map.of("message",message);
    }
    @PostMapping("/InvPage/Admin/addInv")
    public  Map addInv(@RequestBody Invigilation invigilation){
        invigilationService.addInvigilation(invigilation);
        return Map.of("message","添加监考成功");
    }
    @PostMapping("/InvPage/Admin/deleteInv")
    public Map deleteInv(@RequestBody Invigilation invigilation){
        invigilationService.deleteInvigilation(invigilation);
        return Map.of("message","删除监考成功");
    }
    @PostMapping("/InvPage/Admin/updateInv")
    public Map updateInv(@RequestBody Invigilation invigilation){
        invigilationService.updateInvigilation(invigilation);
        return Map.of("message","更新监考成功");
    }
    @PostMapping("/InvPage/Admin/addDis")
    public  Map addDis(@RequestBody Distribution distribution){
        String message="监考没有冲突";
        System.out.println("监考开始时间"+invigilationService.find(distribution.getInvigilation().getInv_id()).getInv_start_time());
        if(!distributionService.checkCrush(invigilationService.find(distribution.getInvigilation().getInv_id()).getInv_start_time(),distribution.getUser().getUser_id()+"")){

            System.out.println("监考冲突");
            message="监考冲突";
        }
        distributionService.addDistribution(distribution);
       User user= userService.find(distribution.getUser().getUser_id());
       user.setUser_inv_times(user.getUser_inv_times()+1);
       userService.updateUser(user);
       if(distribution.getDis_state().equals("指定")){
           System.out.println("该老师已经监考："+distribution.getUser().getUser_inv_times()+"次                                                                       ");
       }
        return Map.of("message",message);
    }
    @PostMapping("/InvPage/Admin/deleteDis")
    public  Map deleteDis(@RequestBody Distribution distribution){
        distributionService.deleteDistribution(distribution);
        return Map.of("message","删除分配成功");
    }
    @PostMapping("/InvPage/Admin/updateDis")
    public Map updateDis(@RequestBody Distribution distribution){
        distributionService.deleteDistribution(distribution);
        return Map.of("message","更新分配成功");
    }
    @PostMapping("/TaskPage/Admin/addTask")
    public  Map addTask(@RequestBody Task task){
        taskService.addTask(task);
        return Map.of("message","添加成功");
    }
    @PostMapping("/TaskPage/Admin/deleteTask")
    public Map deleteTask(@RequestBody Task task){
        taskService.deleteTask(task);
        return Map.of("message","删除用户成功");
    }
    @PostMapping("/TaskPage/Admin/updateTask")
    public Map updateTask(@RequestBody Task task){
        Map map;
        String s="已结束";
        if(!s.equals(taskService.getTask(task.getTask_id()+"").getTask_state())){
        taskService.updateTask(task);
        map=Map.of("message","修改任务成功");
        }
        else {
            map=Map.of("message","修改任务失败，任务已结束");
        }
        return map;
    }
    @PostMapping("/TaskPage/Admin/addTaskRecord")
    public  Map addTaskRecord(@RequestBody TaskRecord taskRecord){
        taskRecordService.addTaskRecord(taskRecord);
        return Map.of("message","添加任务记录成功");
    }
    @PostMapping("/TaskPage/Admin/deleteTaskRecord")
    public  Map deleteTaskRecord(@RequestBody TaskRecord taskRecord){
        taskRecordService.deleteTaskRecord(taskRecord);
        return Map.of("message","删除任务记录成功");
    }
    @PostMapping("/TaskPage/Admin/CloseTask")
    public Map  closeTask(@RequestBody Task task){
        Task task1=taskService.getTask(task.getTask_id()+"");
        task1.setTask_state("已结束");
        taskService.updateTask(task1);
        return Map.of("message","关闭任务成功");
    }

}
