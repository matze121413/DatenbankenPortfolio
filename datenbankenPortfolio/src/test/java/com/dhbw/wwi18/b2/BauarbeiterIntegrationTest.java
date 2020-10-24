package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauarbeiter;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.BauarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BauarbeiterIntegrationTest {

    private static BauarbeiterRepository bauarbeiterRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeClass
    public static void setup() {
        bauarbeiterRepository = new BauarbeiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }



    @AfterClass
    public static void afterAll() {
        mitarbeiterRepository.closeConnection();
        bauarbeiterRepository.closeConnection();
    }

    @Test
    public void createMitarbeiter(){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Matthias");
        mitarbeiter.setNachname("Nachname");
        mitarbeiter.setBerufsbezeichnung("IT");
        mitarbeiter.setGehalt(50000);
        mitarbeiter.setBerufserfahrung(20);

        mitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        assertThat(mitarbeiter.getVorname(), is("Matthias"));
        assertThat(mitarbeiter.getGehalt(), is(50000));
        assertNotNull(mitarbeiter.getMitarbeiterId());

        Bauarbeiter bauarbeiter= new Bauarbeiter();
        bauarbeiter.setMitarbeiter(mitarbeiter);
        bauarbeiter.setMitarbeiterId(mitarbeiter.getMitarbeiterId());
        bauarbeiter.setArbeitsschicht("Frühschicht");
        bauarbeiter.setFachgebiet("Dachdecker");
        bauarbeiter.setGewerkschaft(true);
        bauarbeiter.setSchichtleiter(false);
        bauarbeiter.setTarif(5000);

        bauarbeiter = bauarbeiterRepository.createEntity(bauarbeiter);

        assertThat(bauarbeiter.getArbeitsschicht(), is("Frühschicht"));
        assertThat(bauarbeiter.getMitarbeiterId(), is(mitarbeiter.getMitarbeiterId()));


    }
}
