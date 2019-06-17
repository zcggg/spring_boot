package com.example.demo.service;

import com.example.demo.entity.Distribution;

import com.example.demo.repository.DistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DistributionService {
    Distribution distribution=new Distribution();

    @Autowired
    DistributionRepository distributionRepository;
    public List<Distribution> ListDistribution(){
        List<Distribution> list= distributionRepository.findAll();
        return  list;
    }
    public  Distribution addDistribution(Distribution distribution){
        distributionRepository.save(distribution);
        distributionRepository.refresh(distribution);
        return distribution;
    }
    public void deleteDistribution(Distribution distribution){

        distributionRepository.delete(distribution);
    }
    public Distribution updateDistribution(Distribution invigilation){
        Distribution oldDistribution=distributionRepository.find(invigilation.getDis_id());
        oldDistribution.setDis_state(invigilation.getDis_state());
        return  distributionRepository.save(oldDistribution);
    }
    public Distribution getDistribution(String number){
        int number1=Integer.parseInt(number);
        return  distributionRepository.find(number1);
    }
    public boolean checkCrush( LocalDateTime time,String user_id){
        int number1=Integer.parseInt(user_id);
        List<Distribution> distributionList= distributionRepository.checkCrush(time,number1);
        return distributionList.size()==0;
    }
    public Distribution find(int id){

        return distributionRepository.find(id);
    }
public List<Distribution> findByUserId(String id){
        int id1=Integer.parseInt(id);
        return distributionRepository.findByUser(id1);
}
    public List<Distribution> findDistributionByInv_id(String number){
        int number1=Integer.parseInt(number);
        return distributionRepository.findByInvigilationInv_id(number1);    }
}
