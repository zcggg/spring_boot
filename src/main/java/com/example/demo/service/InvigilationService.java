package com.example.demo.service;

import com.example.demo.entity.Invigilation;
import com.example.demo.entity.User;
import com.example.demo.repository.InvigilationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvigilationService {
    @Autowired
    InvigilationRepository invigilationRepository;
    public List<Invigilation> ListInvigilation(){
        List<Invigilation> list= invigilationRepository.findAll();
        return  list;
    }
    public  Invigilation addInvigilation(Invigilation invigilation){
        invigilationRepository.save(invigilation);
        invigilationRepository.refresh(invigilation);
        return invigilation;
    }
    public void deleteInvigilation(Invigilation invigilation){
        invigilationRepository.delete(invigilation);
    }
    public Invigilation updateInvigilation(Invigilation invigilation){
        Invigilation oldInvigilation=invigilationRepository.find(invigilation.getInv_id());
        oldInvigilation.setInv_location(invigilation.getInv_location());
        return  invigilationRepository.save(oldInvigilation);
    }
    public List<Invigilation> findInvigilationByCourse(String course){
        List<Invigilation> invigilationList=invigilationRepository.find(course);
        return invigilationList;
    }
    public Invigilation getInvigilation(String number){
        int number1=Integer.parseInt(number);
        return  invigilationRepository.find(number1);
    }
    public Invigilation find(int id){
        return invigilationRepository.find(id);
    }
}
