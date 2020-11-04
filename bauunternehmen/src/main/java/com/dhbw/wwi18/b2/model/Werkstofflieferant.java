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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "werkstoffList")
@ToString(exclude = "werkstoffList")
@NoArgsConstructor
@Entity
public class Werkstofflieferant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lieferant_id")
    private Long lieferantId;

    private int erfahrung;

    private String telefonnummer;

    private String name;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Werkstofflieferant_liefert_Werkstoff",
            joinColumns = {@JoinColumn(name = "lieferant_id")},
            inverseJoinColumns = {@JoinColumn(name = "werkstoff_id")}
    )
    private List<Werkstoff> werkstoffList = new ArrayList<>();

    @ManyToOne
    @JoinTable(
            name = "Projektleiter_kontaktiert_Werkstofflieferant",
            joinColumns = {@JoinColumn(name = "lieferant_id")},
            inverseJoinColumns = {@JoinColumn(name = "mitarbeiter_id")})
    private Projektleiter projektleiter;
}
