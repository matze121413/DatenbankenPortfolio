package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "bauprojektList")
@ToString(exclude = "bauprojektList")
@NoArgsConstructor
public class Bauschutt {
    @Id
    @Column(name = "bauschutt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bauschuttId;

    private String art;

    private int gewicht;

    private int kilopreis;

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
