package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Anfrage;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.AnfrageRepository;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnfrageIntegrationTest {

    private static AnfrageRepository anfrageRepository;

    @BeforeAll
    public static void setup() {anfrageRepository = new AnfrageRepository();}

    @AfterAll
    public static void afterAll() {
        anfrageRepository.closeConnection();
    }

    @Test
    public void createAnfrage(){
        createNewAnfrage();
    }

    @Test
    public void getAnfrage(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Anfrage anfrage = createNewAnfrage();
        Anfrage savedAnfrage = anfrageRepository.findById(anfrage.getAnfrageId());
        assertThat(savedAnfrage, is(anfrage));
    }

    @Test
    public void updateAnfrage(){
        Anfrage anfrage = createNewAnfrage();
        anfrage.setAnzRaueme(5);
        Anfrage updatedAnfrage = anfrageRepository.updateWithMerge(anfrage);

        assertThat(updatedAnfrage, is(anfrage));
    }

    @Test
    public void deleteAnfrage(){
        Anfrage anfrage = createNewAnfrage();
        anfrageRepository.deleteEntity(anfrage);

        Anfrage deletedAnfrage = anfrageRepository.findById(anfrage.getAnfrageId());
        assertNull(deletedAnfrage);

    }

    private Anfrage createNewAnfrage(){
        Anfrage anfrage = new Anfrage();
        anfrage.setAnzRaueme(3);
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
