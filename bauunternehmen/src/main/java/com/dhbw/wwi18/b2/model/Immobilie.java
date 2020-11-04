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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "bauprojekt")
@ToString(exclude = "bauprojekt")
@NoArgsConstructor
public class Immobilie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilie_id")
    private Long immobilieId;

    private String farbe;

    private String status;

    private int flaeche;

    @ManyToOne
    @JoinTable(
            name = "Kunde_erhaelt_Immobilie",
            joinColumns = { @JoinColumn(name = "immobilie_id")},
            inverseJoinColumns = { @JoinColumn(name = "kunde_id")})
    private Kunde kunde;

    @ManyToOne
    @JoinTable(
            name = "Ingenieur_gibtFrei_Immobilie",
            joinColumns = { @JoinColumn(name = "immobilie_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Ingenieur ingenieur;

    @OneToOne
    @JoinTable(
            name = "Bauprojekt_stelltFertig_Immobilie",
            joinColumns = {@JoinColumn(name = "immobilie_id")},
            inverseJoinColumns = {@JoinColumn(name = "bauprojekt_id")})
    private Bauprojekt bauprojekt;
}
