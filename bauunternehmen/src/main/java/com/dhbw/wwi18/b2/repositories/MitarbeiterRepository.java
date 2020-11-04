package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Mitarbeiter;

public class MitarbeiterRepository extends GenericRepository<Mitarbeiter, Long> {

    public MitarbeiterRepository() {
        super(Mitarbeiter.class);
    }
}
