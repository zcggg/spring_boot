package com.example.demo;

import com.example.demo.component.Timer;
import com.example.demo.entity.*;
import com.example.demo.repository.DistributionRepository;
import com.example.demo.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    InvigilationService invigilationService;
    @Autowired
    DistributionService distributionService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void test01(){
        //User user=new User("11111","11111");
        User user=new User("33333","22222");
        userService.addUser(user);
        System.out.println(user.getUser_password());

}
@Test
    public void test02(){
        User user=userService.find(1);
        user.setUser_password("11111");
        userService.updateUser(user);
}
@Test
    public void test03(){
        User user=userService.find(5);
        userService.deleteUser(user);
}
@Test
    public void test04(){
       List<User> list = userService.ListUser();
       for(int i=0;i<list.size();i++){
           System.out.println(list.get(i).getUser_id());
       }
}
    @Test
    public void test06(){
        Invigilation invigilation=new Invigilation();
        invigilation.setInv_course(".......程序设计");
        invigilation.setInv_location("909");
        invigilationService.addInvigilation(invigilation);
    }
    @Test
   public void test07(){
        User user=new User();
        userService.addUser(user);
        Invigilation invigilation=new Invigilation();
        Distribution distribution=new Distribution();
        distribution.setInvigilation(invigilation);
        invigilationService.addInvigilation(invigilation);
        distributionService.addDistribution(distribution);
    }
    @Test
    public void test08(){
        User user=userService.findUserByName("11111");
         System.out.print(user.getUser_authority());
    }
    @Autowired
    TaskRecordService taskRecordService;
    @Test
    public void test09(){
       List<TaskRecord> taskRecordList= taskRecordService.findTaskRecordByUser_id("1");
        System.out.print(taskRecordList.get(0).getTr_id());

    }

    @Autowired
    TaskService taskService;
    @Autowired
    DistributionRepository distributionRepository;

       @Test
    public  void test11(){
        List<Task> taskList=taskService.ListTask();
        System.out.print(distributionRepository.checkCrush(invigilationService.getInvigilation("1").getInv_start_time(), 1));
        for(int i=0;i<taskList.size();i++){
            System.out.println(taskList.get(i).getTask_start_time());
        }
       }
    }
    /*@Test
    public void test10(){
        List<Distribution> distributionList=distributionService.findDistributionByUser_id("1");
        System.out.print(distributionList.get(0).getDis_id());
    }*/

