package com.example.demo.repository;

import com.example.demo.entity.Distribution;
import com.example.demo.entity.TaskRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DistributionRepository extends CustomizedRepository<Distribution, Integer> {
    @Query("SELECT d FROM Distribution d WHERE d.dis_id=:id")
    Distribution find(@Param("id") int number);
    @Query("SELECT d FROM Distribution d WHERE d.user.user_id=:id")
    List<Distribution> findByUser(@Param("id") int  number);
    @Query("SELECT d FROM Distribution d WHERE d.invigilation.inv_id=:id")
    List<Distribution> findByInvigilationInv_id(@Param("id") int  number);
    @Query("SELECT d FROM Distribution d WHERE d.invigilation.inv_start_time=:time and d.user.user_id=:id")
    List<Distribution> checkCrush(@Param("time") LocalDateTime time, @Param("id") int user_id);
}
