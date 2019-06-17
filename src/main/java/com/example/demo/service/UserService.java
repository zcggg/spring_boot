package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    public List<User> ListUser(){
        List<User> list= userRepository.findAll();
        return  list;
    }
    public User findUserByName(String user_name){

        return userRepository.findByName(user_name);
    }
    public  User addUser(User user){
        userRepository.save(user);
        userRepository.refresh(user);
        return user;
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    public User updateUser(User user){
        User oldUser=userRepository.find(user.getUser_id());
        oldUser.setUser_password(user.getUser_password());
        oldUser.setUser_authority(user.getUser_authority());
        oldUser.setUser_inv_times(oldUser.getUser_inv_times());
        oldUser.setUser_pt(user.getUser_pt());
        oldUser.setUser_sum(user.getUser_sum());
        oldUser.setUser_tel(user.getUser_tel());
        return  userRepository.save(oldUser);
    }
    public User getUser(String number){
        int number1=Integer.parseInt(number);
        return userRepository.find(number1);
    }
    public User find(int id){
        return userRepository.find(id);
    }
}
