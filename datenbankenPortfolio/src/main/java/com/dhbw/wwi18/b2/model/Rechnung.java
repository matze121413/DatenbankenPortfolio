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
public class Rechnung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rechnung_id")
    private Long rechnungId;

    private int preis;

    private String zahlungsart;

    private String status;

    private int frist;


    @ManyToOne
    @JoinTable(
            name = "Kunde_bezahlt_Rechnung",
            joinColumns = { @JoinColumn(name = "rechnung_id")},
            inverseJoinColumns = { @JoinColumn(name = "kunde_id")})
    private Kunde kunde;

    @ManyToOne
    @JoinTable(
            name = "Sachbearbeiter_stelltAus_Rechnung",
            joinColumns = { @JoinColumn(name = "rechnung_id")},
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id")})
    private Mitarbeiter mitarbeiter;
}
