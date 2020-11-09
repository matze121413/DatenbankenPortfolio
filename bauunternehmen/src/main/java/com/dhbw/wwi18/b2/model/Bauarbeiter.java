package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = {"bautechnikList", "werkstoffList"})
@ToString(exclude = {"bautechnikList", "werkstoffList"})
@NoArgsConstructor
public class Bauarbeiter {

    @Id
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiterId;

    private boolean schichtleiter;

    private String arbeitsschicht;

    private int tarif;

    private String fachgebiet;

    private boolean gewerkschaft;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauarbeiter_verwendet_Bautechnik",
            joinColumns = { @JoinColumn(name = "mitarbeiter_id") },
            inverseJoinColumns = { @JoinColumn(name = "bautechnik_id") }
    )
    private List<Bautechnik> bautechnikList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauarbeiter_verwendet_Werkstoff",
            joinColumns = { @JoinColumn(name = "mitarbeiter_id") },
            inverseJoinColumns = { @JoinColumn(name = "werkstoff_id") }
    )
    private List<Werkstoff> werkstoffList = new ArrayList<>();
}
