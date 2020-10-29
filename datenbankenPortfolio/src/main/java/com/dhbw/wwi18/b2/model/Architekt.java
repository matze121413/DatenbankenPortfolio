package com.dhbw.wwi18.b2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = {})
@ToString(exclude = {})
@NoArgsConstructor
public class Architekt {
    @Id
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private boolean selbstaendig;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

    private String fachrichtung;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;
}
