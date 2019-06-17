package com.example.demo.service;

import com.example.demo.entity.Notify;
import com.example.demo.repository.NotifyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotifyService {
    @Autowired
    NotifyRepository notifyRepository;
    public List<Notify> findNotifyByUser_id(String user_id){
        int number1=Integer.parseInt(user_id);
      return  notifyRepository.find(number1);
    }
}
