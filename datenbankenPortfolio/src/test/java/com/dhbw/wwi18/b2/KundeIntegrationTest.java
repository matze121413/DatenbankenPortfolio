package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Anfrage;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.model.Kunde;
import com.dhbw.wwi18.b2.repositories.AnfrageRepository;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import com.dhbw.wwi18.b2.repositories.KundeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KundeIntegrationTest {

    private static KundeRepository kundeRepository;

    @BeforeAll
    public static void setup() {kundeRepository = new KundeRepository();}

    @AfterAll
    public static void afterAll() {
        kundeRepository.closeConnection();
    }

    @Test
    public void createKunde(){
        createNewKunde();
    }

    @Test
    public void getKunde(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Kunde kunde = createNewKunde();
        Kunde savedKunde = kundeRepository.findById(kunde.getKundeId());
        assertThat(savedKunde, is(kunde));
    }

    @Test
    public void updateKunde(){
        Kunde kunde = createNewKunde();
        kunde.setNachname("Schulz");
        Kunde updatedKunde = kundeRepository.updateWithMerge(kunde);

        assertThat(updatedKunde, is(kunde));
    }

    @Test
    public void deleteKunde(){
        Kunde kunde = createNewKunde();
        kundeRepository.deleteEntity(kunde);

        Kunde deletedKunde = kundeRepository.findById(kunde.getKundeId());
        assertNull(deletedKunde);

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
}