package com.dhbw.wwi18.b2.repositories;


import com.dhbw.wwi18.b2.connection.ConnectionHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;


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


    public void delete(T entity ) {
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


    public T save(T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist( entity );
        transaction.commit();

        em.clear();
        return entity;
    }


    public T update(T entity ) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        T result = em.merge( entity );
        transaction.commit();
        em.clear();
        return result;
    }


    public void closeConnection() {
        em.close();
    }


    public EntityManager getEntityManager() {
        return em;
    }

}