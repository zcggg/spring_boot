package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.TypeCache;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRecordService taskRecordService;
    @Autowired
    private InvigilationService invigilationService;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    NotifyService notifyService;
    @GetMapping("/MainPage")
    public Map main(@RequestAttribute String user_id){
        List<Invigilation> invigilationList=invigilationService.ListInvigilation();
        if(invigilationList.size()>10){
            invigilationList.subList(invigilationList.size()-10, invigilationList.size());
        }
        List<Task> taskList=taskService.ListTask();
        Map map=Map.of("taskList",taskList,"invigilationList",invigilationList);
        return map;
    }
    @GetMapping("/UserPage/ListAll")
    public Map listAll(){
        //Sort sort=new Sort(new  Sort.Order(Sort.Direction.DESC,"id");
        List<User> userList=userService.ListUser();
        Map map=Map.of("userList",userList);
        return map;
    }
    @GetMapping("/UserPage/findUserByName/{name}")
    public Map findUserByName(@PathVariable String name){
        User user = userService.findUserByName(name);
        Map map;
        if(user==null){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND,"未找到该用户");
        }
        else {
        map=Map.of("user",user);
        }
        return map;
    }
    @GetMapping("/InvPage/ListAll")
    public Map listAllInv(){
        List<Invigilation> invigilationList=invigilationService.ListInvigilation();
        Map map=Map.of("invigilationList",invigilationList);
        return map;
    }
    @GetMapping("/InvPage/ListAllDis")
    public Map listAllDis(){
        List<Distribution> distributionList=distributionService.ListDistribution();
        Map map=Map.of("distributionList",distributionList);
        return map;
    }
    @GetMapping("/InvPage/findInvigilationByCourse/{course}")
    public Map findInvigilationByCourse(@PathVariable String course){
        List<Invigilation> invigilationList=invigilationService.findInvigilationByCourse(course);
        Map map=Map.of("invigilationList",invigilationList);
        return  map;
    }
    @GetMapping("/InvPage/findDistributionByInv_id/{id}")
    public  Map findDisByIdInv_id(@PathVariable String id){
       List<Distribution>distributionList= distributionService.findDistributionByInv_id(id);
       Map map=Map.of("distributionList",distributionList);
       return map;
    }
    @GetMapping("/TaskPage/ListAll")
    public Map listAllTask(){
        List<Task> taskList=taskService.ListTask();
        Map map=Map.of("taskList",taskList);
        return map;
    }
    @GetMapping("/TaskPage/find/{id}")
    public Map findTaskById(@PathVariable String id){
        Task task = taskService.getTask(id);
        Map map=Map.of("Task",task);
        return map;
    }
    @GetMapping("/TaskPage/ListAllTaskRecord")
    public Map listAllTaskRecord(){
        List<TaskRecord> taskRecordList=taskRecordService.ListTaskRecord();
        Map map=Map.of("taskRecordList",taskRecordList);
        return map;
    }
    @GetMapping("/TaskPage/findTaskRecord/{id}")
    public  Map findTaskRecordById(@PathVariable String id){
        TaskRecord taskRecord= taskRecordService.getTaskRecord(id);
        Map map=Map.of("taskRecord",taskRecord);
        return map; }
    @PostMapping("/TaskPage/updateTaskRecord")
    public void updateTaskRecord(@RequestBody TaskRecord taskRecord){
        taskRecordService.updateTaskRecord(taskRecord);
    }
    @PostMapping("/MyPage/updateUser")
    public Map updateMyself(@RequestBody User user,@RequestAttribute String user_id){
        User user1=userService.getUser(user_id);
        user1.setUser_name(user.getUser_name());
        if(user.getUser_pt()!=null){
            user1.setUser_pt(user.getUser_pt());
        }
        if(user.getUser_tel()+""!=""){
            user1.setUser_tel(user.getUser_tel());
        }
        if (user.getUser_sum()!=null)
        {
            user1.setUser_sum(user.getUser_sum());
        }
        userService.updateUser(user1);
        return  Map.of("message","个人修改成功");
    }
    @GetMapping("/MyPage/user")
    public Map  myUser(@RequestAttribute String user_id){
        User user= userService.getUser(user_id);
        Map map=Map.of("user",user);
        return map;
    }
    @GetMapping("/MyPage/TaskRecord")
    public Map myTaskRecord(@RequestAttribute String user_id){
        List<TaskRecord> taskRecordListList=taskRecordService.findTaskRecordByUser_id(user_id);
        Map map=Map.of("taskRecordListList",taskRecordListList);
        return map;
    }
    @GetMapping("/MyPage/Distribution")
    public Map myDistribution(@RequestAttribute String user_id){
        List<Distribution> distributionList=distributionService.findByUserId(user_id);
        Map map=Map.of("distributionList",distributionList);
        return map;
    }
    @GetMapping("MyPage/Notify")
    public Map myNotify(@RequestAttribute String user_id){
        List<Notify> notifyList=notifyService.findNotifyByUser_id(user_id);
        Map map=Map.of("notifyList",notifyList);
        return map;
    }
}
