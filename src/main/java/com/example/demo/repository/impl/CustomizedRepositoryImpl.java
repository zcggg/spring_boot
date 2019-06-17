package com.example.demo.repository.impl;

import com.example.demo.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
public class CustomizedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedRepository<T, ID> {
    private EntityManager entityManager;


    public CustomizedRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager=entityManager;
    }

    @Override
    public T refresh(T t) {
        entityManager.refresh(t);
        return t;
    }


}