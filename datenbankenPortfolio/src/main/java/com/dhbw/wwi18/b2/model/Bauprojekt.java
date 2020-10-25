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
@Table(name = "Bauprojekt")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"bauschuttList", "mitarbeiterList","bautechnikList"})
@ToString(exclude = {"bauschuttList", "mitarbeiterList","bautechnikList"})
@NoArgsConstructor
public class Bauprojekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bauprojekt_id")
    private Long bauprojektId;

    @Column
    private int startDatum;

    @Column
    private int endDatum;

    @Column
    private int gewinn;

    @Column
    private int kosten;

    @Column
    private int frist;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauprojekt_bringtHervor_Bauschutt",
            joinColumns = { @JoinColumn(name = "bauprojekt_id") },
            inverseJoinColumns = { @JoinColumn(name = "bauschutt_id") }
    )
    private List<Bauschutt> bauschuttList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Mitarbeiter_beteiligtAn_Bauprojekt",
            joinColumns = { @JoinColumn(name = "bauprojekt_id") },
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id") }
    )
    private List<Mitarbeiter> mitarbeiterList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauprojekt_benoetigt_Bautechnik",
            joinColumns = { @JoinColumn(name = "bauprojekt_id") },
            inverseJoinColumns = { @JoinColumn(name = "bautechnik_id") }
    )
    private List<Bautechnik> bautechnikList = new ArrayList<>();
}
