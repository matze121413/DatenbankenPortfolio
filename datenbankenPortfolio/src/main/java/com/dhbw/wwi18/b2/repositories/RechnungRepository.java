package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Rechnung;

public class RechnungRepository extends GenericRepository<Rechnung, Long> {
    public RechnungRepository(){super(Rechnung.class);}
}
