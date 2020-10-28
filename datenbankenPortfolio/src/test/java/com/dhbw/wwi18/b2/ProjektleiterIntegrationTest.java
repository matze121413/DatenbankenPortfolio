package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Projektleiter;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.ProjektleiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjektleiterIntegrationTest {

    private ProjektleiterRepository projektleiterRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        projektleiterRepository.closeConnection();
    }

    @Test
    public void createBauarbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        createNewProjektleiter(mitarbeiter);
    }

    @Test
    public void getProjektleiter() {
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);
        Projektleiter savedProjektleiter = projektleiterRepository.findById(projektleiter.getMitarbeiterId());
        assertThat(savedProjektleiter, is(projektleiter));

    }

    @Test
    public void updateProjektleiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);
        projektleiter.setGesamtProjektanzahl(43);
        projektleiter.setAktProjektanzahl(2);
        Projektleiter updatedProjektleiter = projektleiterRepository.updateWithMerge(projektleiter);

        assertThat(updatedProjektleiter, is(projektleiter));
    }

    @Test
    public void deleteMitarbeiterDeletesProjektleiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);
        mitarbeiterRepository.deleteEntity(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        Projektleiter deletedProjektleiter = projektleiterRepository.findById(projektleiter.getMitarbeiterId());
        assertNull(deletedMitarbeiter);
        assertNull(deletedProjektleiter);

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

    private Projektleiter createNewProjektleiter(Mitarbeiter savedMitarbeiter) {
        Projektleiter projektleiter = new Projektleiter();
        projektleiter.setAktProjektanzahl(1);
        projektleiter.setGesamtProjektanzahl(42);
        projektleiter.setMitarbeiter(savedMitarbeiter);
        projektleiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        projektleiter = projektleiterRepository.createEntity(projektleiter);

        assertThat(projektleiter.getGesamtProjektanzahl(), is(42));
        assertThat(projektleiter.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return projektleiter;
    }
}
