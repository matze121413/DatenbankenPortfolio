package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Bauprojekt;

public class BauprojektRepository extends GenericRepository<Bauprojekt, Long> {

    public BauprojektRepository(){
        super(Bauprojekt.class);
    }
}
