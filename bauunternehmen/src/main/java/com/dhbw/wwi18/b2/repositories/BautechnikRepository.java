package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Bautechnik;

public class BautechnikRepository   extends GenericRepository<Bautechnik, Long> {

    public BautechnikRepository() {
        super(Bautechnik.class);
    }
}
