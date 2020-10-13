package com.dhbw.wwi18.b2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long mitarbeiterId;

    @Column(name = "name")
    private String name;

    @Column(name = "mitarbeiterAlter")
    private Integer alter;
}
