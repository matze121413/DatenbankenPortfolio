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


    private KundeRepository kundeRepository;
    private ImmobilieRepository immobilieRepository;

    @BeforeAll
    public void setup() {
        kundeRepository = new KundeRepository();
        immobilieRepository = new ImmobilieRepository();
    }

    @AfterAll
    public void done() {
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

        Immobilie savedImmobilie1 = immobilieRepository.update(immobilie1);
        Immobilie savedImmobilie2 = immobilieRepository.update(immobilie2);

        assertThat(savedImmobilie1.getKunde(), is(kunde));
        assertThat(savedImmobilie2.getKunde(), is(kunde));
    }

    private Immobilie createNewImmobilie(){
        Immobilie immobilie = new Immobilie();
        immobilie.setFarbe("gelb");
        immobilie.setStatus("fertig");
        immobilie.setFlaeche(150);

        return immobilieRepository.save(immobilie);
    }
    private Kunde createNewKunde(){
        Kunde kunde = new Kunde();
        kunde.setVorname("Bert");
        kunde.setNachname("Schmitz");
        kunde.setStrasse("Berliner Weg");
        kunde.setOrt("Berlin");
        kunde.setPlz("53343");

        return kundeRepository.save(kunde);
    }
}
