package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Sachbearbeiter;
import com.dhbw.wwi18.b2.model.Sachbearbeiter;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;

import com.dhbw.wwi18.b2.repositories.SachbearbeiterRepository;
import com.dhbw.wwi18.b2.repositories.SachbearbeiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SachbearbeiterIntegrationTest {

    private SachbearbeiterRepository sachbearbeiterRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        sachbearbeiterRepository = new SachbearbeiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        sachbearbeiterRepository.closeConnection();
    }

    @Test
    public void createBauarbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        createNewSachbearbeiter(mitarbeiter);
    }

    @Test
    public void getSachbearbeiter() {
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Sachbearbeiter sachbearbeiter = createNewSachbearbeiter(mitarbeiter);
        Sachbearbeiter savedSachbearbeiter = sachbearbeiterRepository.findById(sachbearbeiter.getMitarbeiterId());
        assertThat(savedSachbearbeiter, is(sachbearbeiter));

    }

    @Test
    public void updateSachbearbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Sachbearbeiter sachbearbeiter = createNewSachbearbeiter(mitarbeiter);
        sachbearbeiter.setTarif(8);
        sachbearbeiter.setAnzRechnungen(60);
        Sachbearbeiter updatedSachbearbeiter = sachbearbeiterRepository.updateWithMerge(sachbearbeiter);

        assertThat(updatedSachbearbeiter, is(sachbearbeiter));
    }

    @Test
    public void deleteMitarbeiterDeletesSachbearbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Sachbearbeiter sachbearbeiter = createNewSachbearbeiter(mitarbeiter);
        mitarbeiterRepository.deleteEntity(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        Sachbearbeiter deletedSachbearbeiter = sachbearbeiterRepository.findById(sachbearbeiter.getMitarbeiterId());
        assertNull(deletedMitarbeiter);
        assertNull(deletedSachbearbeiter);

    }

    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        mitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        //stichprobenartig Felder testen, da davon ausgegangen werden kann, dass Erstellung damit funktioniert hat
        assertThat(mitarbeiter.getVorname(), is("Horst"));
        assertThat(mitarbeiter.getGehalt(), is(3000));
        //Die MitarbeiterId sollte nicht statische erzeugt werden, kann aber nie null sein
        assertNotNull(mitarbeiter.getMitarbeiterId());

        return mitarbeiter;
    }
    private Sachbearbeiter createNewSachbearbeiter(Mitarbeiter savedMitarbeiter) {
        Sachbearbeiter sachbearbeiter = new Sachbearbeiter();
        sachbearbeiter.setTarif(5);
        sachbearbeiter.setMitarbeiter(savedMitarbeiter);
        sachbearbeiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        sachbearbeiter = sachbearbeiterRepository.createEntity(sachbearbeiter);

        assertThat(sachbearbeiter.getTarif(), is(5));
        assertThat(sachbearbeiter.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return sachbearbeiter;
    }

}
