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
@EqualsAndHashCode(exclude = "vertrag")
@ToString(exclude = "vertrag")
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
            name = "Sachbearbeiter_stelltAus_Rechnung",
            joinColumns = {@JoinColumn(name = "rechnung_id")},
            inverseJoinColumns = {@JoinColumn(name = "mitarbeiter_id")})
    private Sachbearbeiter sachbearbeiter;

    @OneToOne
    @JoinColumn(name = "vertrag_id")
    Vertrag vertrag;
}
