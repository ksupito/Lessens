package com.example.project.repository;

import com.example.project.model.EmployeeInfo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class EmployeeInfoRepositoryImpl implements EmployeeInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeInfo getInformation(int idUser) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeInfo> query = builder.createQuery(EmployeeInfo.class);
        Root root = query.from(EmployeeInfo.class);
        query.where(builder.equal(root.get("employeeId"), idUser));
        return entityManager.createQuery(query)
                .setHint("org.hibernate.cacheable", true)
                .setHint("org.hibernate.readOnly", true)
                .setHint("org.hibernate.cacheRegion", "CacheEmployeeInfo")
                .getSingleResult();
    }
}
