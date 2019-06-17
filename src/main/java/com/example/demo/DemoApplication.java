package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.impl.CustomizedRepositoryImpl;
import com.example.demo.service.InvigilationService;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomizedRepositoryImpl.class)
@EnableScheduling
public class DemoApplication {
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected Hibernate5Module module() {
        Hibernate5Module module = new Hibernate5Module();
        // 序列化延迟加载对象的ID
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return module;
    }
}
