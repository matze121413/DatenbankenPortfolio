package com.dhbw.wwi18.b2.connection;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionHelper.class);


    public static EntityManager getConnection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "BauwesenORM" );
        EntityManager em = entityManagerFactory.createEntityManager();

        LOG.debug("# --- Connection established ---");

        return em;
    }

}