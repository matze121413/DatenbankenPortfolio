package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Immobilie;

public class ImmobilieRepository extends GenericRepository<Immobilie, Long> {
    public ImmobilieRepository(){super(Immobilie.class);}
}
