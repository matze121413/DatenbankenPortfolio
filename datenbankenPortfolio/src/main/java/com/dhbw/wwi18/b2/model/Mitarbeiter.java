package com.dhbw.wwi18.b2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Mitarbeiter")
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "nachname")
    private String nachname;

    @Column(name = "berufsbezeichnung")
    private String berufsbezeichnung;

    @Column(name = "berufserfahrung")
    private int berufserfahrung;

    @Column(name = "gehalt")
    private int gehalt;
}
