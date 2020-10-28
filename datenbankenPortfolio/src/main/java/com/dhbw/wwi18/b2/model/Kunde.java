package com.dhbw.wwi18.b2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kunde {
    @Id
    @Column(name = "kunde_id")
    private Long mitarbeiterId;

    private String vorname;

    private String nachname;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;
}
