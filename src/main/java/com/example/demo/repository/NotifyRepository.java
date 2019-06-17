package com.example.demo.repository;

import com.example.demo.entity.Notify;
import com.example.demo.entity.TaskRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyRepository extends CustomizedRepository<Notify, Integer>{
    @Query("SELECT no FROM Notify no WHERE no.user.user_id=:id")
    List<Notify> find(@Param("id") int number);
}
