package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "projektleiter")
@ToString(exclude = "projektleiter")
@NoArgsConstructor
public class Architekt {
    @Id
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private boolean selbststaendig;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

    private String fachrichtung;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @OneToOne
    @JoinTable(
            name = "Projektleiter_kontaktiert_Architekt",
            joinColumns = {@JoinColumn(name = "arch_mitarbeiter_id")},
            inverseJoinColumns = {@JoinColumn(name = "proj_mitarbeiter_id")})
    private Projektleiter projektleiter;
}
