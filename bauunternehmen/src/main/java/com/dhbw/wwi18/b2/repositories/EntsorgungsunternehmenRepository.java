package com.dhbw.wwi18.b2.repositories;

import com.dhbw.wwi18.b2.model.Entsorgungsunternehmen;

public class EntsorgungsunternehmenRepository extends GenericRepository<Entsorgungsunternehmen, Long> {

    public EntsorgungsunternehmenRepository(){
        super(Entsorgungsunternehmen.class);
    }

}
