package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MitarbeiterIntegrationTest {

    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void createMitarbeiter() {
        createNewMitarbeiter();
    }

    @Test
    public void getMitarbeiter() {
        //man muss zunächst einen Werkstoff erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Mitarbeiter savedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        assertThat(savedMitarbeiter, is(mitarbeiter));
    }

    @Test
    public void updateMitarbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        mitarbeiter.setGehalt(3200);
        Mitarbeiter updatedMitarbeiter = mitarbeiterRepository.update(mitarbeiter);

        assertThat(updatedMitarbeiter, is(mitarbeiter));
    }

    @Test
    public void deleteWerkstoff() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        mitarbeiterRepository.delete(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        assertNull(deletedMitarbeiter);

    }

    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        mitarbeiter = mitarbeiterRepository.save(mitarbeiter);

        //stichprobenartig Felder testen, da davon ausgegangen werden kann, dass Erstellung damit funktioniert hat
        assertThat(mitarbeiter.getVorname(), is("Horst"));
        assertThat(mitarbeiter.getGehalt(), is(3000));
        //Die MitarbeiterId sollte nicht statische erzeugt werden, kann aber nie null sein
        assertNotNull(mitarbeiter.getMitarbeiterId());

        return mitarbeiter;
    }
}
