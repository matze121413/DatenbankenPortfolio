package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Werkstofflieferant;

public class WerkstofflieferantRepository extends GenericRepository<Werkstofflieferant, Long> {
    public WerkstofflieferantRepository() {
        super(Werkstofflieferant.class);
    }
}
