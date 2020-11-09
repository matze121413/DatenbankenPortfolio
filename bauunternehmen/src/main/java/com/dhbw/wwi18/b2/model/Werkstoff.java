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
@EqualsAndHashCode(exclude = {"bauprojektList", "werkstofflieferantList", "bauarbeiterList"})
@ToString(exclude = {"bauprojektList", "werkstofflieferantList", "bauarbeiterList"})
@NoArgsConstructor
public class Werkstoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "werkstoff_id")
    private Long werkstoffId;

    private String art;

    private int gewicht;

    private int kilopreis;

    private int menge;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauprojekt_benoetigt_Werkstoff",
            joinColumns = {@JoinColumn(name = "werkstoff_id")},
            inverseJoinColumns = {@JoinColumn(name = "bauprojekt_id")}
    )
    private List<Bauprojekt> bauprojektList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Werkstofflieferant_liefert_Werkstoff",
            joinColumns = {@JoinColumn(name = "werkstoff_id")},
            inverseJoinColumns = {@JoinColumn(name = "lieferant_id")}
    )
    private List<Werkstofflieferant> werkstofflieferantList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauarbeiter_verwendet_Werkstoff",
            joinColumns = { @JoinColumn(name = "werkstoff_id") },
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id") }
    )
    private List<Bauarbeiter> bauarbeiterList = new ArrayList<>();
}
