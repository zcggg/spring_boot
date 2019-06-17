package com.example.demo.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Invigilation {
    private String inv_course;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inv_id;
    private String inv_location;
    private LocalDateTime inv_start_time;
    private LocalDateTime inv_end_time;
    private int inv_nr;
    @OneToMany(mappedBy = "invigilation")
    private List<Distribution> distributions;
}
