package org.poc.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<T, I extends Serializable> {

    @Inject
    EntityManager em;
    private Class<T> persistentClass;

    protected BaseRepositoryImpl(){

    }

    protected BaseRepositoryImpl(Class<T> type) {
        this();
        this.persistentClass = type;
    }

    public T save(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(entity);
        em.flush();
        t.commit();
        return entity;
    }

    public T update(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(entity);
        em.flush();
        t.commit();
        return entity;
    }


    public void delete(I id) {
        T entity = find(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        T mergedEntity = em.merge(entity);
        em.remove(mergedEntity);
        em.flush();
        tx.commit();
    }

    public List<T> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        query.from(persistentClass);
        return em.createQuery(query).getResultList();
    }

    public T find(I id) {
        return em.find(persistentClass, id);
    }


}
