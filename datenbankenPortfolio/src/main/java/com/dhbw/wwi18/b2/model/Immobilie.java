package com.dhbw.wwi18.b2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
    @JoinColumn(name = "bauprojekt_id")
    private Bauprojekt bauprojekt;
}
