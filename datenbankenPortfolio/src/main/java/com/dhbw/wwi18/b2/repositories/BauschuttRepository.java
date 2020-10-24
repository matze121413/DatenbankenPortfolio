package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Bauschutt;

public class BauschuttRepository  extends GenericRepository<Bauschutt, Long> {

    public BauschuttRepository() {
        super(Bauschutt.class);
    }
}
