package com.dhbw.wwi18.b2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Data
@Table(name = "Bauschutt")
public class Bauschutt {
    @Id
    @Column(name = "bauschutt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bauschuttId;

    @Column
    private String art;

    @Column
    private int gewicht;

    @Column
    private int kilopreis;

    @Column
    private int menge;

    @ManyToOne
    @JoinTable(
            name = "Entsorgungsunternehmen_entsorgt_Bauschutt",
            joinColumns = { @JoinColumn(name = "bauschutt_id")},
                    inverseJoinColumns = { @JoinColumn(name = "entsorgung_id")})
    private Entsorgungsunternehmen entsorgungsunternehmen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Bauprojekt_bringtHervor_Bauschutt",
            joinColumns = { @JoinColumn(name = "bauschutt_id") },
            inverseJoinColumns = { @JoinColumn(name = "bauprojekt_id") }
    )
    private List<Bauprojekt> bauprojektList = new ArrayList<>();
}
