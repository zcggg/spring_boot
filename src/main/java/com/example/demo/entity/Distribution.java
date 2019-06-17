package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dis_id;
    @ManyToOne
    private User user;
    private String dis_state="不指定";
    @ManyToOne
    private Invigilation invigilation;
}
