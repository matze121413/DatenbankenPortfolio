package com.dhbw.wwi18.b2.connection;



import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
public class ConnectionHelper {

    public static EntityManager getConnection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "BauwesenORM" );
        EntityManager em = entityManagerFactory.createEntityManager();

        log.debug("# --- Connection established ---");

        return em;
    }

}