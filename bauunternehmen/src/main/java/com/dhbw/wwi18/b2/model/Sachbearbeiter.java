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
public class Sachbearbeiter {
    @Id
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private int tarif;

    private int anzRechnungen;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;


}
