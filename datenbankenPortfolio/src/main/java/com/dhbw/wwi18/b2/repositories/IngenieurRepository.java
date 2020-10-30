package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Ingenieur;

public class IngenieurRepository extends GenericRepository<Ingenieur, Long> {
    public IngenieurRepository(){super(Ingenieur.class);}
}
