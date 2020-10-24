package com.dhbw.wwi18.b2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Entsorgungsunternehmen")
public class Entsorgungsunternehmen {
    @Id
    @Column(name = "entsorgung_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entsorgungId;

    @Column
    private String name;

    @Column
    private String telefonnummer;

    @Column
    private String abholzeit;

    @Column
    private int abholtag;

    @Column
    private int erfahrung;
}
