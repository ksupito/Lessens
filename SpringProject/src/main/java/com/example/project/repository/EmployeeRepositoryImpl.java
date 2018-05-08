package com.example.project.repository;

import com.example.project.model.Employee;
import com.example.project.model.EmployeeInfo;
import com.example.project.repository.connection.DbConnection;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> getUsers(String lastNameWasEntered, int limit, int fromIndex) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        query.where(builder.like(root.get("lastName"), "%" + lastNameWasEntered + "%"));
        List<Employee> listEmployee = entityManager.createQuery(query).setFirstResult(fromIndex).setMaxResults(limit).getResultList();
        return listEmployee;
    }

    public int checkCountRows(String lastNameWasEntered) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Employee> root = query.from(Employee.class);
        query.select(builder.count(root)).where(builder.like(root.get("lastName"), "%" + lastNameWasEntered + "%"));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        long countLong = typedQuery.getSingleResult();
        int count = (int) countLong;
        return count;
    }
}

