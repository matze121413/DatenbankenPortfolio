package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Immobilie;
import com.dhbw.wwi18.b2.repositories.ImmobilieRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImmobilienIntegrationTest {
    private static ImmobilieRepository immobilieRepository;

    @BeforeAll
    public static void setup() {immobilieRepository = new ImmobilieRepository();}

    @AfterAll
    public static void afterAll() {
        immobilieRepository.closeConnection();
    }

    @Test
    public void createImmobilie(){
        createNewImmobilie();
    }

    @Test
    public void getImmobilie(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Immobilie immobilie = createNewImmobilie();
        Immobilie savedImmobilie = immobilieRepository.findById(immobilie.getImmobilieId());
        assertThat(savedImmobilie, is(immobilie));
    }

    @Test
    public void updateImmobilie(){
        Immobilie immobilie = createNewImmobilie();
        immobilie.setFarbe("blau");
        Immobilie updatedImmobilie = immobilieRepository.update(immobilie);

        assertThat(updatedImmobilie, is(immobilie));
    }

    @Test
    public void deleteImmobilie(){
        Immobilie immobilie = createNewImmobilie();
        immobilieRepository.delete(immobilie);

        Immobilie deletedImmobilie = immobilieRepository.findById(immobilie.getImmobilieId());
        assertNull(deletedImmobilie);

    }

    private Immobilie createNewImmobilie(){
        Immobilie immobilie = new Immobilie();
        immobilie.setFarbe("gelb");
        immobilie.setStatus("fertig");
        immobilie.setFlaeche(150);

        Immobilie savedImmobilie = immobilieRepository.save(immobilie);

        assertNotNull(savedImmobilie.getImmobilieId());
        assertThat(immobilie.getFarbe(), is("gelb"));
        return savedImmobilie;
    }
}
