package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Ingenieur;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.IngenieurRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngenieurIntegrationTest {

    private IngenieurRepository ingenieurRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        ingenieurRepository = new IngenieurRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        ingenieurRepository.closeConnection();
    }

    @Test
    public void createBauarbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        createNewIngenieur(mitarbeiter);
    }

    @Test
    public void getIngenieur() {
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Ingenieur ingenieur = createNewIngenieur(mitarbeiter);
        Ingenieur savedIngenieur = ingenieurRepository.findById(ingenieur.getMitarbeiterId());
        assertThat(savedIngenieur, is(ingenieur));

    }

    @Test
    public void updateIngenieur() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Ingenieur ingenieur = createNewIngenieur(mitarbeiter);
        ingenieur.setSelbständig(true);
        ingenieur.setOrt("Karlsruhe");
        Ingenieur updatedIngenieur = ingenieurRepository.updateWithMerge(ingenieur);

        assertThat(updatedIngenieur, is(ingenieur));
    }

    @Test
    public void deleteMitarbeiterDeletesIngenieur() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Ingenieur ingenieur = createNewIngenieur(mitarbeiter);
        mitarbeiterRepository.deleteEntity(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        Ingenieur deletedIngenieur = ingenieurRepository.findById(ingenieur.getMitarbeiterId());
        assertNull(deletedMitarbeiter);
        assertNull(deletedIngenieur);

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

    private Ingenieur createNewIngenieur(Mitarbeiter savedMitarbeiter) {
        Ingenieur ingenieur = new Ingenieur();
        ingenieur.setSelbständig(false);
        ingenieur.setStrasse("im Sand");
        ingenieur.setOrt("Rheinbreitbach");
        ingenieur.setPlz("56846");
        ingenieur.setMitarbeiter(savedMitarbeiter);
        ingenieur.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        ingenieur = ingenieurRepository.createEntity(ingenieur);

        assertThat(ingenieur.getStrasse(), is("im Sand"));
        assertThat(ingenieur.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return ingenieur;
    }
}
