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
@Table
public class Entsorgungsunternehmen {
    @Id
    @Column(name = "entsorgung_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entsorgungId;

    private String name;

    private String telefonnummer;

    private String abholzeit;

    private int abholtag;

    private int erfahrung;
}
