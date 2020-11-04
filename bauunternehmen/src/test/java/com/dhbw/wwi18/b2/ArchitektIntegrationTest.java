package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Architekt;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.ArchitektRepository;
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
public class ArchitektIntegrationTest {


    private ArchitektRepository architektRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        architektRepository = new ArchitektRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        architektRepository.closeConnection();
    }

    @Test
    public void createArchitekt() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        createNewArchitekt(mitarbeiter);
    }

    @Test
    public void getArchitekt(){
        //man muss zunächst einen Architekt erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Architekt architekt = createNewArchitekt(mitarbeiter);
        Architekt savedArchitekt = architektRepository.findById(architekt.getMitarbeiterId());
        assertThat(savedArchitekt, is(architekt));

    }

    @Test
    public void updateArchitekt(){
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Architekt architekt = createNewArchitekt(mitarbeiter);
        architekt.setFachrichtung("Innenarchitekt");
        Architekt updatedArchitekt = architektRepository.update(architekt);

        assertThat(updatedArchitekt, is(architekt));
    }

    @Test
    public void deleteMitarbeiterDeletesArchitekt(){
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Architekt architekt = createNewArchitekt(mitarbeiter);
        mitarbeiterRepository.delete(mitarbeiter);

        Mitarbeiter deletedMitarbeiter = mitarbeiterRepository.findById(mitarbeiter.getMitarbeiterId());
        Architekt deletedArchitekt = architektRepository.findById(architekt.getMitarbeiterId());
        assertNull(deletedMitarbeiter);
        assertNull(deletedArchitekt);

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

    private Architekt createNewArchitekt(Mitarbeiter savedMitarbeiter){
        Architekt architekt= new Architekt();
        architekt.setMitarbeiter(savedMitarbeiter);
        architekt.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());
        architekt.setSelbststaendig(true);
        architekt.setStrasse("Pfad");
        architekt.setOrt("Köln");
        architekt.setPlz("50667");
        architekt.setFachrichtung("Aussenarchitekt");
        architekt = architektRepository.save(architekt);

        assertThat(architekt.getStrasse(), is("Pfad"));
        assertThat(architekt.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return architekt;
    }

}
