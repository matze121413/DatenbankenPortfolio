package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Bauarbeiter;

public class BauarbeiterRepository  extends GenericRepository<Bauarbeiter, Long> {

    public BauarbeiterRepository() {
        super(Bauarbeiter.class);
    }
}
