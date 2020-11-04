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
public class Skizze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skizze_id")
    private Long skizzeId;

    private int flaeche;

    private String detailgrad;

    private String raum;

    @ManyToOne
    @JoinTable(
            name = "Ingenieur_prueft_Skizze",
            joinColumns = { @JoinColumn(name = "skizze_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Ingenieur ingenieur;

    @ManyToOne
    @JoinTable(
            name = "Architekt_entwirft_Skizze",
            joinColumns = { @JoinColumn(name = "skizze_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Architekt architekt;

    @ManyToOne
    @JoinTable(
            name = "Vertrag_enthaelt_Skizze",
            joinColumns = { @JoinColumn(name = "skizze_id")},
            inverseJoinColumns = { @JoinColumn(name = "vertrag_id")})
    private Vertrag vertrag;
}
