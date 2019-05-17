/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.integration.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.core.GenericTypeResolver;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 * @param <T>
 * @param <K>
 */
public class JpaGeneralDaoImpl<T, K extends Serializable> implements JpaGeneralDao<T, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    public JpaGeneralDaoImpl() {
        Class<?>[] typeArguments = GenericTypeResolver
                .resolveTypeArguments(this.getClass(), JpaGeneralDaoImpl.class);
        if (typeArguments != null) {
            clazz = (Class<T>) typeArguments[0];
        }
    }

    public JpaGeneralDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getEntityClass() {
        return clazz;
    }

    @Override
    public void setEntityClass(Class<T> entityClass) {
        this.clazz = entityClass;
    }

    @Override
    public void insert(T obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(T obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(T obj) {
        entityManager.remove(entityManager.merge(obj));
    }

    @Override
    public void deleteById(K key) {
        T entity = entityManager.getReference(clazz, key);
        entityManager.remove(entity);
    }

    @Override
    public T findById(K key) {
        return entityManager.find(clazz, key);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);
        q.from(clazz);
        return entityManager.createQuery(q).getResultList();
    }

    @Override
    public long getCount() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(clazz)));
        return entityManager.createQuery(q).getSingleResult();
    }

    @Override
    public List<T> findByAttributes(Map<String, Object> attributes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);
        Root<T> e = q.from(clazz);
        Predicate[] predicates = new Predicate[attributes.size()];
        int i = 0;
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            String key = attribute.getKey();
            Object value = attribute.getValue();
            predicates[i++] = cb.equal(e.get(key), value);
        }
        q = q.where(predicates);
        return entityManager.createQuery(q).getResultList();
    }

}
