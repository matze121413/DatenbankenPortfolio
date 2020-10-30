package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Immobilie;
import com.dhbw.wwi18.b2.model.Kunde;
import com.dhbw.wwi18.b2.repositories.ImmobilieRepository;
import com.dhbw.wwi18.b2.repositories.KundeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KundeErhaeltImmobilieIntegrationTest {


    private static KundeRepository kundeRepository;
    private static ImmobilieRepository immobilieRepository;

    @BeforeAll
    public static void setup() {
        kundeRepository = new KundeRepository();
        immobilieRepository = new ImmobilieRepository();
    }

    @AfterAll
    public static void done() {
        kundeRepository.closeConnection();
        immobilieRepository.closeConnection();
    }

    @Test
    public void setKundeInImmobilie() {
        Kunde kunde = createNewKunde();

        Immobilie immobilie1 = createNewImmobilie();
        Immobilie immobilie2 = createNewImmobilie();

        immobilie1.setKunde(kunde);
        immobilie2.setKunde(kunde);

        Immobilie savedImmobilie1 = immobilieRepository.updateWithMerge(immobilie1);
        Immobilie savedImmobilie2 = immobilieRepository.updateWithMerge(immobilie2);

        assertThat(savedImmobilie1.getKunde(), is(kunde));
        assertThat(savedImmobilie2.getKunde(), is(kunde));
    }

    private Immobilie createNewImmobilie(){
        Immobilie immobilie = new Immobilie();
        immobilie.setFarbe("gelb");
        immobilie.setStatus("fertig");
        immobilie.setFlaeche(150);

        Immobilie savedImmobilie = immobilieRepository.createEntity(immobilie);

        assertNotNull(savedImmobilie.getImmobilieId());
        assertThat(immobilie.getFarbe(), is("gelb"));
        return savedImmobilie;
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
        assertThat(kunde.getStrasse(), is("Berliner Weg"));
        return savedKunde;
    }
}
