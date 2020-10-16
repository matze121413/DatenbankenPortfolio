package com.dhbw.wwi18.b2.model;

import lombok.Data;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bauarbeiter")
@Getter
@Setter
@EqualsAndHashCode(exclude = "bautechnikList")
@ToString(exclude = "bautechnikList")
@NoArgsConstructor
public class Bauarbeiter {

    @Id
    @Column(name = "mitarbeiter_id")
    Long mitarbeiterId;

    @Column
    boolean schichtleiter;

    @Column
    String arbeitsschicht;

    @Column
    int tarif;

    @Column
    String fachgebiet;

    @Column
    boolean gewerkschaft;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @ManyToMany
    @JoinTable(
            name = "Bauarbeiter_verwendet_Bautechnik",
            joinColumns = { @JoinColumn(name = "mitarbeiter_id") },
            inverseJoinColumns = { @JoinColumn(name = "bautechnik_id") }
    )
    private List<Bautechnik> bautechnikList = new ArrayList<>();
}
