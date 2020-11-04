package com.dhbw.wwi18.b2.repositories;


import com.dhbw.wwi18.b2.connection.ConnectionHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;


public abstract class GenericRepository<T, ID extends Serializable> {

    protected EntityManager em = ConnectionHelper.getConnection();

    protected final Class<T> entityClass;


    protected GenericRepository( Class<T> entityClass ) {
        this.entityClass = entityClass;
    }


    public T findById( ID id ) {
        T entity=  em.find( entityClass, id );
        //führt sonst dazu, dass Entities nicht gecacht werden
        // Cacheing kann bei Beziehungen zwischen Entites zu Problemen führen kann
        em.clear();
        return entity;
    }


    public List<T> findAll() {
        CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery( entityClass );
        c.select( c.from( entityClass ) );
        List<T> entityList = em.createQuery( c ).getResultList();
        em.clear();
        return entityList;
    }


    public void deleteEntity( T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        if ( em.contains( entity ) ) {
            em.remove( entity );
        } else {
            em.remove( em.merge( entity ) );
        }

        transaction.commit();
        em.clear();
    }


    public T createEntity( T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist( entity );
        //em.refresh( entity );
        transaction.commit();

        em.clear();
        return entity;
    }


    public T updateWithMerge( T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        T result = em.merge( entity );
        transaction.commit();
        em.clear();
        return result;
    }


    public T updateWithoutMerge( T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        transaction.commit();
        em.clear();
        return entity;
    }


    public EntityTransaction getEntityTransaction() {
        reconnectToDatabase();
        return em.getTransaction();
    }


    public void closeConnection() {
        em.close();
    }


    public EntityManager getEntityManager() {
        return em;
    }


    public void reconnectToDatabase() {
        if ( em == null || !em.isOpen() ) {
            em = ConnectionHelper.getConnection();
        }
    }

}