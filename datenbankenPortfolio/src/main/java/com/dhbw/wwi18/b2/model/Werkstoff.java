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

@Table(name="Werkstoff")
@Getter
@Setter
@EqualsAndHashCode(exclude = "bauprojektList")
@ToString(exclude = "bauprojektList")
@NoArgsConstructor
@Entity
public class Werkstoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "werkstoff_id")
    private Long werkstoffId;

    @Column
    private String art;

    @Column
    private int gewicht;

    @Column
    private int kilopreis;

    @Column
    private int menge;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Bauprojekt_benoetigt_Werkstoff",
            joinColumns = { @JoinColumn(name = "werkstoff_id") },
            inverseJoinColumns = { @JoinColumn(name = "bauprojekt_id") }
    )
    private List<Bauprojekt> bauprojektList = new ArrayList<>();
}
