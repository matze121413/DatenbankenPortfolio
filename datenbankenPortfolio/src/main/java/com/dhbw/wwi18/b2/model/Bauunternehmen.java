package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Bauunternehmen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unternehmen_id")
    private Long unternehmenId;

    private String name;

    private String telefonnummer;

    @Column(name = "straße")
    private String strasse;

    private String ort;

    private String plz;

}
