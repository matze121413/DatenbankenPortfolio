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
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(exclude = "")
@ToString(exclude = "")
@NoArgsConstructor
public class WerkzeugUndMaschinenverleih {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verleih_id")
    private Long verleihId;

    private int erfahrung;

    private String name;

    private String telefonnummer;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

    @ManyToOne
    @JoinTable(
            name = "Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih",
            joinColumns = {@JoinColumn(name = "verleih_id")},
            inverseJoinColumns = {@JoinColumn(name = "mitarbeiter_id")})
    private Projektleiter projektleiter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik",
            joinColumns = {@JoinColumn(name = "verleih_id")},
            inverseJoinColumns = {@JoinColumn(name = "bautechnik_id")})
    private List<Bautechnik> bautechnikList;
}
