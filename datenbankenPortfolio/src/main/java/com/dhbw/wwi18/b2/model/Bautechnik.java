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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = {"bauarbeiterList", "bauprojektList"})
@ToString(exclude = {"bauarbeiterList", "bauprojektList"})
@NoArgsConstructor
public class Bautechnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bautechnik_id")
    private Long bautechnikId;

    private int leihdauer;

    private String art;

    private String marke;

    private String zustand;

    private int tagespreis;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauarbeiter_verwendet_Bautechnik",
            joinColumns = { @JoinColumn(name = "bautechnik_id") },
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id") }
    )
    private List<Bauarbeiter> bauarbeiterList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauprojekt_benoetigt_Bautechnik",
            joinColumns = { @JoinColumn(name = "bautechnik_id") },
            inverseJoinColumns = { @JoinColumn(name = "bauprojekt_id") }
    )
    private List<Bauprojekt> bauprojektList = new ArrayList<>();
}
