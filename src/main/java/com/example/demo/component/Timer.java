package com.example.demo.component;

import com.example.demo.entity.Invigilation;
import com.example.demo.repository.InvigilationRepository;
import com.example.demo.service.InvigilationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Timer {
    @Scheduled(cron = "*/10 * * * * ?")
    public static void sys1(){
        System.out.println(1);
    }
}
