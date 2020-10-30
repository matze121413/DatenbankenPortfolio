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
@EqualsAndHashCode(exclude = "rechnung")
@ToString(exclude = "rechnung")
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
            joinColumns = {@JoinColumn(name = "vertrag_id")},
            inverseJoinColumns = {@JoinColumn(name = "kunde_id")})
    private Kunde kunde;

    @ManyToOne
    @JoinTable(
            name = "Projektleiter_erstellt_Vertrag",
            joinColumns = {@JoinColumn(name = "vertrag_id")},
            inverseJoinColumns = {@JoinColumn(name = "mitarbeiter_id")})
    private Projektleiter projektleiter;

    @OneToOne(mappedBy = "vertrag")
    Rechnung rechnung;
}
