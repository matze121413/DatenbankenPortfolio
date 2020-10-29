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
public class Vertrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vertrag_id")
    private Long vertragId;

    private int preis;

    private boolean unterschrift;

    private int laufzeit;

    private String gegenstand;

    @ManyToOne
    @JoinTable(
            name = "Kunde_erhaelt_Vertrag",
            joinColumns = { @JoinColumn(name = "vertrag_id")},
            inverseJoinColumns = { @JoinColumn(name = "kunde_id")})
    private Kunde kunde;

    @ManyToOne
    @JoinTable(
            name = "Projektleiter_erstellt_Vertrag",
            joinColumns = { @JoinColumn(name = "vertrag_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Projektleiter projektleiter;
}
