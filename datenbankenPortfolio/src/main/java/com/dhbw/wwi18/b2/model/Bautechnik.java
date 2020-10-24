package com.dhbw.wwi18.b2.model;

import lombok.Data;
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
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bautechnik")
@Data
@Getter
@Setter
@EqualsAndHashCode(exclude = "bauarbeiterList")
@ToString(exclude = "bauarbeiterList")
@NoArgsConstructor
public class Bautechnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bautechnik_id")
    private Long bautechnikId;

    @Column
    private int leihdauer;

    @Column
    private String art;

    @Column
    private String marke;

    @Column
    private String zustand;

    @Column
    private int tagespreis;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Bauarbeiter_verwendet_Bautechnik",
            joinColumns = { @JoinColumn(name = "bautechnik_id") },
            inverseJoinColumns = { @JoinColumn(name = "mitarbeiter_id") }
    )
    private List<Bauarbeiter> bauarbeiterList = new ArrayList<>();
}
