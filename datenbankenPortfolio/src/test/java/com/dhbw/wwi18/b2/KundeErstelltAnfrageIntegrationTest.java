package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.*;
import com.dhbw.wwi18.b2.repositories.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dhbw.wwi18.b2.model.Anfrage;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.AnfrageRepository;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KundeErstelltAnfrageIntegrationTest {


    private static KundeRepository kundeRepository;
    private static AnfrageRepository anfrageRepository;

    @BeforeAll
    public static void setup() {
        kundeRepository = new KundeRepository();
        anfrageRepository = new AnfrageRepository();
    }

    @AfterAll
    public static void done() {
        kundeRepository.closeConnection();
        anfrageRepository.closeConnection();
    }

    @Test
    public void setKundeInAnfrage() {
        Kunde kunde = createNewKunde();

        Anfrage anfrage1 = createNewAnfrage();
        Anfrage anfrage2 = createNewAnfrage();

        anfrage1.setKunde(kunde);
        anfrage2.setKunde(kunde);

        Anfrage savedAnfrage1 = anfrageRepository.updateWithMerge(anfrage1);
        Anfrage savedAnfrage2 = anfrageRepository.updateWithMerge(anfrage2);

        assertThat(savedAnfrage1.getKunde(), is(kunde));
        assertThat(savedAnfrage2.getKunde(), is(kunde));
    }

    private Kunde createNewKunde(){
        Kunde kunde = new Kunde();
        kunde.setVorname("Bert");
        kunde.setNachname("Schmitz");
        kunde.setStrasse("Berliner Weg");
        kunde.setOrt("Berlin");
        kunde.setPlz("53343");

        Kunde savedKunde = kundeRepository.createEntity(kunde);

        assertNotNull(savedKunde.getKundeId());
        assertThat(kunde.getStrasse(), is("Schofer"));
        return savedKunde;
    }

    private Anfrage createNewAnfrage(){
        Anfrage anfrage = new Anfrage();
        anfrage.setAnzRaeume(4);
        anfrage.setStrasse("Binger");
        anfrage.setOrt("Mühlheim");
        anfrage.setPlz("76185");
        anfrage.setFlaeche(53);
        anfrage.setFarbe("rot");
        anfrage.setPreisvorstellung(500000);

        Anfrage savedAnfrage = anfrageRepository.createEntity(anfrage);

        assertNotNull(savedAnfrage.getAnfrageId());
        assertThat(anfrage.getStrasse(), is("Schofer"));
        return savedAnfrage;
    }
}