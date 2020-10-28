package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Anfrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anfrage_id")
    private Long anfrageId;

    private int anzRaeume;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

    private int flaeche;

    private String farbe;

    private int preisvorstellung;

    @ManyToOne
    @JoinTable(
            name = "Bauunternehmen_erhaelt_Anfrage",
            joinColumns = { @JoinColumn(name = "anfrage_id")},
            inverseJoinColumns = { @JoinColumn(name = "unternehmen_id")})
    private Bauunternehmen bauunternehmen;

    @ManyToOne
    @JoinTable(
            name = "Projektleiter_bearbeitet_Anfrage",
            joinColumns = { @JoinColumn(name = "anfrage_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Mitarbeiter mitarbeiter;

    @ManyToOne
    @JoinTable(
            name = "Kunde_erstellt_Anfrage",
            joinColumns = { @JoinColumn(name = "anfrage_id")},
            inverseJoinColumns = { @JoinColumn(name = "kunde_id")})
    private Kunde kunde;
}


