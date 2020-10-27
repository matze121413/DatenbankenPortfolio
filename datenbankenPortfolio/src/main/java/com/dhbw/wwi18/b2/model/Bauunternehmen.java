package com.dhbw.wwi18.b2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String strasse;

    private String ort;

    private String plz;

}
