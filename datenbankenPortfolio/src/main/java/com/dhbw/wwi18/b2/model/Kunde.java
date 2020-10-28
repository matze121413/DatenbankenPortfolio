package com.dhbw.wwi18.b2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kunde {
    @Id
    private Long mitarbeiterId;
}
