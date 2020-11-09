package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauarbeiter;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.BauarbeiterRepository;
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
public class BauarbeiterIntegrationTest {

    private BauarbeiterRepository bauarbeiterRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        bauarbeiterRepository = new BauarbeiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        bauarbeiterRepository.closeConnection();
    }

    @Test
    public void createBauarbeiter() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        createNewBauarbeiter(mitarbeiter);
    }

    @Test
    public void getBauarbeiter(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Bauarbeiter bauarbeiter = createNewBauarbeiter(mitarbeiter);
        Bauarbeiter savedBauarbeiter = bauarbeiterRepository.findById(bauarbeiter.getMitarbeiterId());
        assertThat(savedBauarbeiter, is(bauarbeiter));

    }

    @Test
    public void updateBauarbeiter(){
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Bauarbeiter bauarbeiter = createNewBauarbeiter(mitarbeiter);
        bauarbeiter.setSchichtleiter(true);
        Bauarbeiter updatedBauarbeiter = bauarbeiterRepository.update(bauarbeiter);

        assertThat(updatedBauarbeiter, is(bauarbeiter));
    }

    @Test
    public void deleteMitarbeiterDeletesBauarbeiter(){
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Bauarbeiter bauarbeiter = createNewBauarbeiter(mitarbeiter);
        mitarbeiterRepository.delete(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        Bauarbeiter deletedBauarbeiter = bauarbeiterRepository.findById(bauarbeiter.getMitarbeiterId());
        assertNull(deletedMitarbeiter);
        assertNull(deletedBauarbeiter);

    }

    private Mitarbeiter createNewMitarbeiter(){
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

    private Bauarbeiter createNewBauarbeiter(Mitarbeiter savedMitarbeiter){
        Bauarbeiter bauarbeiter= new Bauarbeiter();
        bauarbeiter.setMitarbeiter(savedMitarbeiter);
        bauarbeiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());
        bauarbeiter.setArbeitsschicht("Frühschicht");
        bauarbeiter.setFachgebiet("Dachdecker");
        bauarbeiter.setGewerkschaft(true);
        bauarbeiter.setSchichtleiter(false);
        bauarbeiter.setTarif(5000);

        bauarbeiter = bauarbeiterRepository.save(bauarbeiter);

        assertThat(bauarbeiter.getArbeitsschicht(), is("Frühschicht"));
        assertThat(bauarbeiter.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return bauarbeiter;
    }


}
