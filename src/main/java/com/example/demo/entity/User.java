package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    public final static String COMMON_USER="普通用户";
    public final static String ADMIN="管理员";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int user_id;
    @Column(unique = true)
    @NotNull
    private String user_name;
    private String user_pt=null;
    private int user_tel;
    private String user_sum=null;
    private int user_inv_times=0;
    // 仅序列化时忽略
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String user_password;
    private String user_authority=User.COMMON_USER;
    @OneToMany(mappedBy = "user")
    private List<Distribution> distributions;
    @OneToMany(mappedBy = "user")
    private List<TaskRecord> taskRecords;
    @OneToMany(mappedBy = "user")
    private List<Notify> notifies;
    public User(String  name,String password){
        this.user_name=name;
        this.user_password=password;
    }
}