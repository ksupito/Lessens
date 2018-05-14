package com.example.project.repository;

import com.example.project.model.Department;
import com.example.project.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department getDepartment(int employeeId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        Join<Department, Employee> join = root.join("managers");
        query.where(criteriaBuilder.equal(join.get("id"), employeeId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
