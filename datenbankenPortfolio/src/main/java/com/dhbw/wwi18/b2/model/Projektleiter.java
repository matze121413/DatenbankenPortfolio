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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "architekt")
@ToString(exclude = "architekt")
@NoArgsConstructor
public class Projektleiter {
    @Id
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private int gesamtProjektanzahl;

    private int aktProjektanzahl;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @OneToOne(mappedBy = "projektleiter")
    private Architekt architekt;

}
