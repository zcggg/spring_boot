package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int no_id;
    @ManyToOne
    private User user;
    private String no_content;
}
