package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Projektleiter;

public class ProjektleiterRepository extends GenericRepository<Projektleiter, Long> {
    public ProjektleiterRepository() {
        super(Projektleiter.class);
    }
}
