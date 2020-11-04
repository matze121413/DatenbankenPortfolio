package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Werkstoff;

public class WerkstoffRepository extends GenericRepository<Werkstoff,Long> {
    public WerkstoffRepository(){
        super(Werkstoff.class);
    }
}
