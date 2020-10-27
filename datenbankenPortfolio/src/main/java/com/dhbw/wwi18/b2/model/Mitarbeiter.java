package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Mitarbeiter")
@Getter
@Setter
@EqualsAndHashCode(exclude = "bauprojektList")
@ToString(exclude = "bauprojektList")
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private String vorname;

    private String nachname;

    private String berufsbezeichnung;

    private int berufserfahrung;

    private int gehalt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Mitarbeiter_beteiligtAn_Bauprojekt",
            joinColumns = { @JoinColumn(name = "mitarbeiter_id") },
            inverseJoinColumns = { @JoinColumn(name = "bauprojekt_id") }
    )
    private List<Bauprojekt> bauprojektList = new ArrayList<>();
}
