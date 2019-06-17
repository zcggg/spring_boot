package com.example.demo.repository;

import com.example.demo.entity.Invigilation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface InvigilationRepository extends CustomizedRepository<Invigilation, Integer>{
    @Query("SELECT i FROM Invigilation i WHERE i.inv_id=:id")
    Invigilation find(@Param("id") int number);
    @Query("SELECT i FROM Invigilation i WHERE i.inv_course=:course")
   List<Invigilation> find(@Param("course") String name);
}
