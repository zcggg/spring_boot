package com.example.demo.repository;

import com.example.demo.entity.TaskRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRecordRepository extends CustomizedRepository<TaskRecord, Integer>{
    @Query("SELECT tr FROM TaskRecord tr WHERE tr.Tr_id=:id")
    TaskRecord find(@Param("id") int number);
    @Query("SELECT tr FROM TaskRecord tr WHERE tr.user.user_id=:id")
    List<TaskRecord> findByUser(@Param("id") int  number);
}
